package geometries;

import primitives.*;

import java.util.List;


public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material=new Material();

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return  this;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Color getEmission() {
        return emission;
    }

    public abstract Vector getNormal(Point point);
}
