package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {

    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", _plane=" + plane +
                "} " + super.toString();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // p.x=p0.getx+nb1*dir.getx
        // p.y=p0.gety+nb1*dir.gety
        // p.z=p0.getz+nb1*dir.getz
        // new Point P1(p.x,p.y,p.z)
        // intersections.add
        var intersections = plane.findIntersections(ray);

        if (intersections == null)
            return null;//if there is no intersection with the plane

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        //verify if it's in the triangle
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double nb1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(nb1))
            return null;

        double nb2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(nb2))
            return null;

        double nb3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(nb3))
            return null;

        if ((nb1 > 0 && nb2 > 0 && nb3 > 0) || (nb1 < 0 && nb2 < 0 && nb3 < 0)) {
            //Point P1=new Point(p0.getX()+nb1*v.getX(),p0.getY()+nb1*v.getY(),p0.getZ()+nb1*v.getZ());
            //intersections.add(P1);
            return intersections.stream().map(gp->new GeoPoint(this,gp)).toList();
        }
        return null;
    }
}


