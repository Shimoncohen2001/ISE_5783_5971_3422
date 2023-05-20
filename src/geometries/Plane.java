package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry {

    private Point _q0;
    private Vector _normal;

    /**
     * Constructor for plane with 2 parameters
     * @param q0 Point
     * @param normal Vector
     */
    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalize();
    }

    /**
     * Constructor for plane with 3 parameters
     * @param p1 Point
     * @param p2 Point
     * @param p3 Point
     */
    public Plane(Point p1, Point p2, Point p3)
    {
        _q0 = p1;
        _normal = getNormal(p1);
    }

    /**
     * getter for _q0 field
     * @return _q0 field
     */
    public Point getQ0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    /**
     * getter for _normal field
     * @return _normal field
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * implementation of getNormal from Geometry
     * @param point Point
     * @return getNormal
     */
    @Override
    public Vector getNormal(Point point) {
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        _normal= v1.crossProduct(v2).normalize();
        return getNormal();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _q0.subtract(ray.getP0());//_q0 is a point from the plane and getP0 of the ray returns his origin.
        } catch (IllegalArgumentException e) {
            return null; // It means that there's no intersection.
        }

         /*
         The value of our variable t that we search for is the result of this formula :(p0−l0)⋅n/l⋅n
         p0 is the point of our plane,n is the normal of our plane , l0 is the origin point of the ray  and l is the vector director of the ray.
         So this value helps us to determine the intersection,or not.
         */

        double check = _normal.dotProduct(ray.getDir());

        if (isZero(check)) {
            return null;//It means that the ray is parallel to the plane
        }

        double t = alignZero(_normal.dotProduct(p0Q) / check);//It gives us the t to determine the coordinate of the intersection , so the x,y,z according to the value of t

        if (t < 0) {
            return null;
        } else {
            return List.of(new GeoPoint(this,ray.getPoint(t)));//p=p0+t*v
        }

    }
}
