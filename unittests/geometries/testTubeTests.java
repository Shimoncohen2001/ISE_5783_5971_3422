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

    /**
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersectionsTest() {




        Tube tube1 = new Tube(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d);
        Vector vAxis = new Vector(0, 0, 1);
        Tube tube2 = new Tube(new Ray(new Point(1, 1, 1), vAxis), 1d);
        Ray ray;



        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the tube (0 points)
        ray = new Ray(
                new Point(1, 1, 2),
                new Vector(1, 1, 0)
        );
        assertNull(tube1.findIntersections(ray),"Must not be intersections");

        // TC02: Ray's crosses the tube (2 points)
        ray = new Ray(
                new Point(0, 0, 0),
                new Vector(2, 1, 1)
        );
        List<Point> result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals(2, result.size(),"must be 2 intersections");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 0.2), new Point(2, 1, 1)),
                result, "Bad intersections");

        // TC03: Ray's starts within tube and crosses the tube (1 point)
        ray = new Ray(
                new Point(1, 0.5, 0.5),
                new Vector(2, 1, 1)
        );
        result = tube2.findIntersections(ray);
        assertNotNull( result,"must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Bad intersections");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line is parallel to the axis (0 points)
        // TC11: Ray is inside the tube (0 points)
        ray = new Ray(new Point(0.5, 0.5, 0.5), vAxis);
        assertNull(tube2.findIntersections(ray), "must not be intersections" );

        // TC12: Ray is outside the tube
        ray = new Ray(new Point(0.5, -0.5, 0.5), vAxis);
        assertNull( tube2.findIntersections(ray),"must not be intersections");

        // TC13: Ray is at the tube surface
        ray = new Ray(new Point(2, 1, 0.5), vAxis);
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC14: Ray is inside the tube and starts against axis head
        ray = new Ray(new Point(0.5, 0.5, 1), vAxis);
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC15: Ray is outside the tube and starts against axis head
        ray = new Ray(new Point(0.5, -0.5, 1), vAxis);
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC16: Ray is at the tube surface and starts against axis head
        ray = new Ray(new Point(2, 1, 1), vAxis);
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC17: Ray is inside the tube and starts at axis head
        ray = new Ray(new Point(1, 1, 1), vAxis);
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // **** Group: Ray is orthogonal but does not begin against the axis head
        // TC21: Ray starts outside and the line is outside (0 points)
        ray = new Ray(new Point(0, 2, 2), new Vector(1, 1, 0));
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC22: The line is tangent and the ray starts before the tube (0 points)
        ray = new Ray(new Point(0, 2, 2), new Vector(1, 0, 0));
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC23: The line is tangent and the ray starts at the tube (0 points)
        ray = new Ray(new Point(1, 2, 2), new Vector(1, 0, 0));
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC24: The line is tangent and the ray starts after the tube (0 points)
        ray = new Ray(new Point(2, 2, 2), new Vector(1, 0, 0));
        assertNull( tube2.findIntersections(ray), "must not be intersections");

        // TC25: Ray starts before (2 points)
        ray = new Ray(new Point(0, 0, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals(2, result.size(), "must be 2 intersections");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 2), new Point(2, 1, 2)), result,
                "Bad intersections");

        // TC26: Ray starts at the surface and goes inside (1 point)
        ray = new Ray(new Point(0.4, 0.2, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals(1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(2, 1, 2)), result, "Bad intersections");

        // TC27: Ray starts inside (1 point)
        ray = new Ray(new Point(1, 0.5, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(2, 1, 2)), result, "Bad intersections");

        // TC28: Ray starts at the surface and goes outside (0 points)
        ray = new Ray(new Point(2, 1, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Bad intersections");

        // TC29: Ray starts after
        ray = new Ray(new Point(4, 2, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Bad intersections");
        // TC30: Ray starts before and crosses the axis (2 points)

        ray = new Ray(new Point(1, -1, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 2, result.size(), "must be 2 intersections");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, 0, 2), new Point(1, 2, 2)), result,
                "Bad intersections");

        // TC31: Ray starts at the surface and goes inside and crosses the axis
        ray = new Ray(new Point(1, 0, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Bad intersections");

        // TC32: Ray starts inside and the line crosses the axis (1 point)
        ray = new Ray(new Point(1, 0.5, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Bad intersections");

        // TC33: Ray starts at the surface and goes outside and the line crosses the
        // axis (0 points)
        ray = new Ray(new Point(1, 2, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result,"Bad intersections");

        // TC34: Ray starts after and crosses the axis (0 points)
        ray = new Ray(new Point(1, 3, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result,"Bad intersections");

        // TC35: Ray starts at the axis
        ray = new Ray(new Point(1, 1, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Bad intersections");

        // **** Group: Ray is orthogonal to axis and begins against the axis head
        // TC41: Ray starts outside and the line is outside (
        ray = new Ray(new Point(0, 2, 1), new Vector(1, 1, 0));
        assertNull(tube2.findIntersections(ray), "must not be intersections");

        // TC42: The line is tangent and the ray starts before the tube
        ray = new Ray(new Point(0, 2, 1), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "must not be intersections");

        // TC43: The line is tangent and the ray starts at the tube
        ray = new Ray(new Point(1, 2, 1), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "must not be intersections");

        // TC44: The line is tangent and the ray starts after the tube
        ray = new Ray(new Point(2, 2, 2), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "must not be intersections");

        // TC45: Ray starts before
        ray = new Ray(new Point(0, 0, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals(2, result.size(), "must be 2 intersections");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 1), new Point(2, 1, 1)), result,
                "Bad intersections");

        // TC46: Ray starts at the surface and goes inside
        ray = new Ray(new Point(0.4, 0.2, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Bad intersections");

        // TC47: Ray starts inside
        ray = new Ray(new Point(1, 0.5, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "must be intersections");
        assertEquals( 1, result.size(), "must be 1 intersections");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Bad intersections");

        // TC48: Ray starts at the surface and goes outside
        ray = new Ray(new Point(2, 1, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Bad intersections");

        // TC49: Ray starts after
        ray = new Ray(new Point(4, 2, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Bad intersections");
        // TC50: Ray starts before and goes through the axis head
        ray = new Ray(new Point(1, -1, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result,"must be intersections");
        assertEquals(2, result.size(), "must be 2 intersections");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, 0, 1), new Point(1, 2, 1)), result,
                "Bad intersections");

    }
}