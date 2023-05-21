package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections.size() == 0) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }
    private Color calcColor(GeoPoint gp,Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission())
                .add(calcLocalEffects(gp,ray));
    }
    private Color calcLocalEffects(GeoPoint point, Ray ray) {
        Vector v = ray.getDir();
        Vector n = point.geometry.getNormal(point.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        double nShininess = point.geometry.getMaterial().nShininess;
        Double3 kd = point.geometry.getMaterial().kD;
        Double3 ks = point.geometry.getMaterial().ks;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(point.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }

        return color;
    }

    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity){
        double lN;
        try {
            lN = l.normalize().dotProduct(n.normalize());
        }
        catch (Exception exception) {
            return lightIntensity.scale(0);
        }
        return lightIntensity.scale(kd.scale(Math.abs(lN)));
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity){

        Vector r = l.add(n.scale(n.dotProduct(l) * -2));
        double vR;
        try {
            vR = v.scale(-1).normalize().dotProduct(r.normalize());
        }
        catch (Exception exception) {
            return lightIntensity.scale(1);
        }

        return lightIntensity.scale(ks.scale (Math.pow(Math.max(0, vR), nShininess)));
    }
}
