package geometries;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import java.util.List;

import static primitives.Util.*;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     *  Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube(new Ray(new Point(0,0,0), new Vector(0,0,1)),1d);
        Vector normal = new Vector(0,1,0);
        assertEquals(tube.getNormal(new Point(0,1,1)), normal, "Bad normal calculation !");
    }
}