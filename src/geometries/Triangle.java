package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Triangle extends Polygon{

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
                "vertices=" + _vertices +
                ", _plane=" + _plane +
                "} " + super.toString();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // p.x=p0.getx+nb1*dir.getx
        // p.y=p0.gety+nb1*dir.gety
        // p.z=p0.getz+nb1*dir.getz
        // new Point P1(p.x,p.y,p.z)
        // intersections.add
        List<Point> intersections = _plane.findIntersections(ray);
        if (intersections == null)
            return null;//if there is no intersection with the plane
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = _vertices.get(0).subtract(p0);//verify if its in the triangle
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);
        double nb1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(nb1))
            return null;
        double nb2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(nb2))
            return null;
        double nb3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(nb3))
            return null;
        if((nb1 > 0 && nb2 > 0 && nb3 > 0) || (nb1 < 0 && nb2 < 0 && nb3 < 0))
        {
            //Point P1=new Point(p0.getX()+nb1*v.getX(),p0.getY()+nb1*v.getY(),p0.getZ()+nb1*v.getZ());
            //intersections.add(P1);
            return intersections;
        }
        else return  null;
    }
}


