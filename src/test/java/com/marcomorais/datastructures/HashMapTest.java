package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class HashMapTest {
    @Test()
    public void test() {
    	HashMap<String, Integer> map = new HashMap<>(); 
        map.add("this",1 );
        map.add("coder",2 );
        map.add("this",4 );
        map.add("hi",5 );

        assertTrue(3 == map.size()); 
        assertTrue(4 == map.remove("this")); 
        assertTrue(null == map.remove("this")); 
        assertTrue(2 == map.size()); 
        assertTrue(false == map.isEmpty()); 
    }
}
