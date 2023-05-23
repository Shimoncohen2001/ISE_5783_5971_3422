package geometries;

import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    //use LinkedList instead of ArrayList for better performance in the future (if needed) - O(1) instead of O(n) for add and remove operations
    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        this();
        Collections.addAll(this.geometries, geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findGeoIntersections(ray);
            if (temp != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(temp);
            }
        }
        return intersections;
    }

}