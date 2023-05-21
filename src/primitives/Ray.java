package primitives;

import geometries.Intersectable;
import geometries.Plane;

import java.util.List;
import java.util.Objects;
import static geometries.Intersectable.GeoPoint;
public class Ray {

    final Point p0;
    final Vector dir;

    /**
     * Constructor for Ray with 2 parameters
     * @param p0 Point
     * @param dir Vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * Get point on ray at a distance from the ray
     * @param t distance from ray
     * @return the point
     */
    public Point getPoint(double t) {
        if (t == 0)
            return p0;
        else {
            try {
                Point point = new Point(p0.add(dir.scale(t)));
                return point;
            } catch (Exception exception) {
                return null;
            }
        }
    }
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listofpoint){
     if(listofpoint==null)
         return null;
     GeoPoint ClosesGeoPoint=null;
     double maxvalue= Double.MAX_VALUE;
     double mindistanceTemp;
     for (int i=0;i<listofpoint.size();i++)
     {
         mindistanceTemp=p0.distance(listofpoint.get(i).point);
         if(mindistanceTemp<maxvalue)
         {
             maxvalue=mindistanceTemp;
             ClosesGeoPoint=listofpoint.get(i);
         }
     }
     return ClosesGeoPoint;
    }
    public Point findClosestPoint(List<Point> intersections) {
        return intersections == null ? null
                : findClosestGeoPoint(intersections.stream().map(gp->new GeoPoint(null,gp)).toList()).point;
    }
}
