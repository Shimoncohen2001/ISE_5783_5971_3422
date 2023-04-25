package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if (p0.equals(_center))
            throw new IllegalArgumentException("Ray's p0 cannot be equal to the center of the Sphere");

        /*
        find intersections using formula:
        𝑢 = (𝑂 − 𝑃0) (vector)
        𝑡𝑚 = 𝑣 ∙ 𝑢
        𝑑 = sqrt(𝑢^2 − 𝑡𝑚^2),  ⇨ if (𝒅 ≥ 𝒓) there are no intersections
        𝑡ℎ = sqrt(𝑟^2 − 𝑑^2)
        t1,t2 = 𝑡𝑚 ± 𝑡ℎ, 𝑃𝑖 = 𝑃0 + 𝑡𝑖,   ⇨ take only 𝒕 > 0
         */


        Vector u = _center.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if (d >= _radius)
            return null; //there is no intersections

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = tm - th;
        double t2 = tm + th;

        //take only t > 0

        if (t1 > 0 && t2 > 0){
            Point p1 = p0.add(v.scale(t1));
            Point p2 = p0.add(v.scale(t2));
            return List.of(p1, p2);
        }

        if (t1 > 0){
            Point p1 = p0.add(v.scale(t1));
            return List.of(p1);
        }
        if (t2 > 0){
            Point p2 = p0.add(v.scale(t2));
            return List.of(p2);
        }

        return null;
    }
}
