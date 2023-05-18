package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    @Override
    public Color traceRay(Ray ray) {
        if(scene.geometries.findIntersections(ray)==null)
            return scene.background;
        List<Point> intersections=scene.geometries.findIntersections(ray);
        Point point=ray.findClosestPoint(intersections);
        return calcColor(point);
    }

    public Color calcColor(Point p){
        return scene.ambientLight.getIntensity();
    }
}
