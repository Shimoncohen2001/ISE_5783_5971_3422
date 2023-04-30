package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * Class for interface intersectable with function that returns
 * list of intersections of a ray with the geometry object
 */

public interface Intersectable {

    /**
     * Function that finds all intersections between geometric element and ray
     * @param ray Ray
     * @return List of points of intersection
     */
    List<Point> findIntersections(Ray ray);
}
