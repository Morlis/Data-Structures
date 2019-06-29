package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ArrayListTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayList() {
    	ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        assertTrue(4 == list.get(4));
        assertTrue(2 == list.get(1));
        assertTrue(3 == list.get(2));

        list.get(6);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testNegative() {
    	ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.get(-1);
    }
}
