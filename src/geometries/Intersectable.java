package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Class for interface intersectable with function that returns
 * list of intersections of a ray with the geometry object
 */

public abstract class Intersectable {

    /**
     * Function that finds all intersections between geometric element and ray
     * @param ray Ray
     * @return List of points of intersection
     */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp->new Point(gp.point)).toList();
    }
    public static class GeoPoint {
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        public Geometry geometry;
        public Point point;
    }
    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
