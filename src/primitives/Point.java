package primitives;

import java.util.Objects;
import static java.lang.Math.sqrt;

public class Point{

    public static final Point ZERO =new Point(0d,0d,0d);
    final Double3 xyz;

    /**
     * Constructor that receive object of Double3 type
     * @param xyz Double3
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructor that receive 3 numbers of double type
     * @param x double
     * @param y double
     * @param z double
     */
    public Point(double x, double y , double z) {
        xyz = new Double3(x,y,z);
    }

    public Point(Point point) {
        xyz = point.xyz;
    }

    /**
     * Getter of point
     * @return the Point in the x axe's
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Getter of point
     * @return the Point in the y axe's
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Getter of point
     * @return the Point in the z axe's
     */
    public double getZ() {
        return xyz.d3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * Method to add a Vector with a Point
     * @param v Vector
     * @return a new Point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * Method to subtract a Point with a Vector
     * @param p Point
     * @return a new Vector
     */
    public Vector subtract(Point p)
    {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Method to calculate the distanceSquared between 2 Points
     * @param p Point
     * @return a distanceSquared (double)
     */
    public double distanceSquared(Point p) {
        double x = (p.xyz.d1 - xyz.d1) * (p.xyz.d1 - xyz.d1);
        double y = (p.xyz.d2 - xyz.d2) * (p.xyz.d2 - xyz.d2);
        double z = (p.xyz.d3 - xyz.d3) * (p.xyz.d3 - xyz.d3);

        return (x + y + z);
    }

    /**
     * Method to calculate the distance between 2 Points
     * @param p Point
     * @return a distance (double)
     */
    public double distance(Point p)
    {
        return sqrt(distanceSquared(p));
    }
}
