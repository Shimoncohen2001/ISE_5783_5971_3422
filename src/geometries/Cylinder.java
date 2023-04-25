package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Cylinder extends Tube implements Geometry{

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
    public List<Point> findIntersections(Ray ray) {

        List<Point> result = new LinkedList<>();
        Vector va = this._axisRay.getDir();
        Point p1 = this._axisRay.getP0();
        Point p2 = p1.add(this._axisRay.getDir().scale(this._height));

        Plane plane1 = new Plane(p1, this._axisRay.getDir()); //get plane of bottom base
        List<Point> result2 = plane1.findIntersections(ray); //intersections with bottom's plane
        if (result2 != null){
            //Add all intersections of bottom's plane that are in the base's bounders
            for (Point point : result2) {
                if (point.equals(p1)){ //to avoid vector ZERO
                    result.add(p1);
                }
                //Formula that checks that point is inside the base
                else if ((point.subtract(p1).dotProduct(point.subtract(p1)) < this._radius * this._radius)){
                    result.add(point);
                }
            }
        }

        List<Point> result1 = super.findIntersections(ray); //get intersections for tube

        if (result1 != null){
            //Add all intersections of tube that are in the cylinder's bounders
            for (Point point:result1) {
                if (va.dotProduct(point.subtract(p1)) > 0 && va.dotProduct(point.subtract(p2)) < 0){
                    result.add(point);
                }
            }
        }

        Plane plane2 = new Plane(p2, this._axisRay.getDir()); //get plane of top base
        List<Point> result3 = plane2.findIntersections(ray); //intersections with top's plane

        if (result3 != null){
            for (Point point : result3) {
                if (point.equals(p2)){ //to avoid vector ZERO
                    result.add(p2);
                }
                //Formula that checks that point is inside the base
                else if ((point.subtract(p2).dotProduct(point.subtract(p2)) < this._radius * this._radius)){
                    result.add(point);
                }
            }
        }

        if (result.size() > 0)
            return result;

        return null;
    }
}
