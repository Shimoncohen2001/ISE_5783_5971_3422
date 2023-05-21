package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Cylinder extends Tube {

    final double _height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        //when point is on the base
        if (point.subtract(_axisRay.getP0()).dotProduct(_axisRay.getDir()) == 0){
            return _axisRay.getDir();
        }

        //when point is on the top
        else if (point.subtract(_axisRay.getP0().add(_axisRay.getDir().scale(_height)))
                .dotProduct(_axisRay.getDir()) == 0){
            return _axisRay.getDir();
        }

        //when point on the surface, same normal as tube
        return super.getNormal(point);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = new LinkedList<>();
        Vector va = this._axisRay.getDir();
        Point p1 = this._axisRay.getP0();
        Point p2 = p1.add(this._axisRay.getDir().scale(this._height));

        Plane plane1 = new Plane(p1, this._axisRay.getDir()); //get plane of bottom base
        List<GeoPoint> result2 = plane1.findGeoIntersections(ray); //intersections with bottom's plane
        if (result2 != null){
            //Add all intersections of bottom's plane that are in the base's bounders
            for (GeoPoint point : result2) {
                if (point.point.equals(p1)){ //to avoid vector ZERO
                    result.add(point);
                }
                //Formula that checks that point is inside the base
                else if ((point.point.subtract(p1).dotProduct(point.point.subtract(p1)) < this._radius * this._radius)){
                    result.add(point);
                }
            }
        }

        List<GeoPoint> result1 = super.findGeoIntersectionsHelper(ray); //get intersections for tube

        if (result1 != null){
            //Add all intersections of tube that are in the cylinder's bounders
            for (GeoPoint point:result1) {
                if (va.dotProduct(point.point.subtract(p1)) > 0 && va.dotProduct(point.point.subtract(p2)) < 0){
                    result.add(point);
                }
            }
        }


        Plane plane2 = new Plane(p2, this._axisRay.getDir()); //get plane of top base
        List<GeoPoint> result3 = plane2.findGeoIntersectionsHelper(ray); //intersections with top's plane

        if (result3 != null){
            for (GeoPoint point : result3) {
                if (point.point.equals(p2)){ //to avoid vector ZERO
                    result.add(point);
                }
                //Formula that checks that point is inside the base
                else if ((point.point.subtract(p2).dotProduct(point.point.subtract(p2)) < this._radius * this._radius)){
                    result.add( point);
                }
            }
        }

        if (result.size() > 0)
            return result;

        return null;
    }


}
