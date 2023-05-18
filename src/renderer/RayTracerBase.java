package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.awt.color.ColorSpace;
import java.util.List;

public abstract class RayTracerBase {
    protected Scene scene;

    public  RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);
}
