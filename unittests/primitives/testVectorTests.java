package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    public void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple dotProduct test
        assertEquals(-28d, v1.dotProduct(v2), 0.00001,
                "dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: dotProduct for orthogonal vectors
        try{
            assertEquals(0d, v1.dotProduct(v3),0.00001,
                    "dotProduct() for orthogonal vectors is not zero");
        } catch (NullPointerException e){}


    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001,
                "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),
                "crossProduct() result is not orthogonal to 1st operand");

        assertTrue(isZero(vr.dotProduct(v3)),
                "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "crossProduct() for parallel vectors does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(5d, new Vector(0, 3, 4).length(),
                "length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(14d, v1.lengthSquared(), 0.00001,
                "lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {

        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertNotSame(v, n,
                "normalized() changes the vector itself");

        assertEquals(1d, n.lengthSquared(), 0.00001,
                "wrong normalized vector length");

        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(n),
                "normalized vector is not in the same direction");

        assertEquals(new Vector(0, 0.6, 0.8), n,
                "wrong normalized vector");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1),
                new Vector(2, 3, 4).add(new Vector(-1, -2, -3)),
                "Wrong vector add");

        try {
            new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
            fail("Add v plus -v must throw exception");
        } catch (IllegalArgumentException e) {}
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(2, 4, 6),
                new Vector(1, 2, 3).scale(2),
                "Wrong vector scale");

        // =============== Boundary Values Tests ==================
        // TC11: test scaling to 0
        try {
            new Vector(1, 2, 3).scale(0d);
            fail("Scale by 0 must throw exception");
        } catch (IllegalArgumentException e) {}
    }
}
