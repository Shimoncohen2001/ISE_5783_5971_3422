package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


public interface Geometry  {

    Vector getNormal(Point point);
}
