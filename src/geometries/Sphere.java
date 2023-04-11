package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{

    final Point _center;
    final double _radius;

    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        Vector v = p.subtract(_center).normalize();
        return v;
    }
}
