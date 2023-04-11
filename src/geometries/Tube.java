package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube implements Geometry {

    final Ray _axisRay;
    final double _radius;

    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        double t = _axisRay.getDir().dotProduct(p.subtract(_axisRay.getP0()));
        Point o = _axisRay.getP0().add(_axisRay.getDir().scale(t));
        return p.subtract(o).normalize();
    }
}