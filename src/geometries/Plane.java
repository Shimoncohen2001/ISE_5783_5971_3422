package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {

    final Point _q0;
    final Vector _normal;

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
        _normal = null;
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
        return v1.crossProduct(v2).normalize();
    }
}
