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

         /*
         We're starting to check if the plane where our triangle is ,intersect the ray ,if not return null,if yes :
        */
        List<Point> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;//Our plan doesn't intersect the ray

        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        //This step is necessary if the ray doesn't start at 0,0,0
        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        //Check every side of the triangle
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double nb1 = alignZero(v.dotProduct(n1));
        if (isZero(nb1)) return null;

        double nb2 = alignZero(v.dotProduct(n2));

        if (nb1 * nb2 <= 0) return null;

        double nb3 = alignZero(v.dotProduct(n3));

        if (nb1 * nb3 <= 0) return null;

        return intersections;
    }
}


