package primitives;

import java.util.Objects;
import static java.lang.Math.sqrt;

public class Point{

    public static final Point ZERO =new Point(0d,0d,0d);
    final Double3 _xyz;

    /**
     * Constructor that receive object of Double3 type
     * @param xyz Double3
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    /**
     * Constructor that receive 3 numbers of double type
     * @param x double
     * @param y double
     * @param z double
     */
    public Point(double x, double y , double z) {
        _xyz = new Double3(x,y,z);
    }

    public Point(Point point) {
        _xyz = point._xyz;
    }

    /**
     * Getter of point
     * @return the Point in the x axe's
     */
    public double getX() {
        return _xyz._d1;
    }

    /**
     * Getter of point
     * @return the Point in the y axe's
     */
    public double getY() {
        return _xyz._d2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(_xyz, point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "_xyz=" + _xyz +
                '}';
    }

    /**
     * Method to add a Vector with a Point
     * @param v Vector
     * @return a new Point
     */
    public Point add(Vector v) {
        return new Point(_xyz.add(v._xyz));
    }

    /**
     * Method to subtract a Point with a Vector
     * @param p Point
     * @return a new Vector
     */
    public Vector subtract(Point p)
    {
        return new Vector(_xyz.subtract(p._xyz));
    }

    /**
     * Method to calculate the distanceSquared between 2 Points
     * @param p Point
     * @return a distanceSquared (double)
     */
    public double distanceSquared(Point p) {
        double x = (p._xyz._d1 - _xyz._d1) * (p._xyz._d1 - _xyz._d1);
        double y = (p._xyz._d2 - _xyz._d2) * (p._xyz._d2 - _xyz._d2);
        double z = (p._xyz._d3 - _xyz._d3) * (p._xyz._d3 - _xyz._d3);

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
