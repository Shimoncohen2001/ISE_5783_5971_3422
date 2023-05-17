package primitives;

import static java.lang.Math.sqrt;

public class Vector extends Point{

    /**
     * Constructor for Vector with 3 parameters
     * @param x double
     * @param y double
     * @param z double
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error! Cannot be Vector(0,0,0)");;
    }

    /**
     * Constructor for Vector with one parameter
     * @param xyz Double3
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error! Cannot be Vector(0,0,0)");
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return xyz.equals(vector.xyz);
    }

    /**
     * Method to add 2 Vectors
     * @param v Vector
     * @return a new Vector
     */
    public Vector add(Vector v) {
        double x = xyz.d1 + v.xyz.d1;
        double y = xyz.d2 + v.xyz.d2;
        double z = xyz.d3 + v.xyz.d3;

        return new Vector(x, y, z);
    }

    /**
     * Method to scale a Vector with a double number
     * @param a double
     * @return a new Vector
     */
    public Vector scale(double a) {
        double x = xyz.d1 * a;
        double y = xyz.d2 * a;
        double z = xyz.d3 * a;

        return new Vector(x, y, z);
    }

    /**
     * Method for the crossProduct between 2 Vectors
     * @param v Vector
     * @return a new Vector
     */
    public Vector crossProduct(Vector v) {
        double x = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double y = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double z = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;

        return new Vector(x, y, z);
    }

    /**
     * Method to calculate the lengthSquared of a Vector
     * @return the lengthSquared of a Vector
     */
    public double lengthSquared() {
        double u1 = xyz.d1 * xyz.d1;
        double u2 = xyz.d2 * xyz.d2;
        double u3 = xyz.d3 * xyz.d3;

        return u1 + u2 + u3;
    }

    /**
     * Method to calculate the length of a Vector
     * @return the length of a Vector
     */
    public double length() {
        return sqrt(lengthSquared());
    }

    /**
     * Method to normalize a Vector
     * @return a normalized Vector
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * Method for the dotProduct between 2 Vectors
     * @param v Vector
     * @return a number (double)
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 +
                xyz.d2 * v.xyz.d2 +
                xyz.d3 * v.xyz.d3;
    }
}
