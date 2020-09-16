package com.bsu;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testRowIsOrdered() {
        int[][] testMatrix = {
                {1,2,3,4,5},
                {5,4,3,2,1},
                {1,2,5,3,4}  };

        assertEquals(Main.rowIsOrdered(testMatrix,0),1);
        assertEquals(Main.rowIsOrdered(testMatrix,1),-1);
        assertEquals(Main.rowIsOrdered(testMatrix,2),0);
    }
}