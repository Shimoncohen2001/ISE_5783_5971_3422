package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTests {

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {

        Vector v = new Vector(2,3,4);
        Point result = new Point(1,2,3).add(v);
        assertEquals(new Point(3,5,7), result);
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {

        Point p1 = new Point(1, 2, 3);
        Vector result = new Point(2, 3, 4).subtract(p1);

        if (!Point.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            fail("ERROR: Point + Vector does not work correctly");

        if (!new Vector(1, 1, 1).equals(result))
            fail("ERROR: Point - Point does not work correctly");
    }


    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {

        Point point = new Point(0.5, 0, -100);
        double resultSquared;
        resultSquared = point.distanceSquared(new Point(0,0,-100));
        System.out.println(resultSquared);
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {

        Point point = new Point(0.5, 0, -100);
        double result;
        result = point.distance(new Point(0,0,-100));
        System.out.println(result);
    }
}