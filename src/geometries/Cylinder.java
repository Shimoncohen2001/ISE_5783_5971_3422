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
}
