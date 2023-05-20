package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Color getEmission() {
        return emission;
    }

    public abstract Vector getNormal(Point point);
}
