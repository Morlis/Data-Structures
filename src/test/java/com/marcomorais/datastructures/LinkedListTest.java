package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LinkedListTest {
    @Test()
    public void test() {
    	LinkedList<String> list = new LinkedList<>();
    	
    	list.add("Second");
    	list.addFirst("First");
    	list.addLast("Third");
    	list.add("Fourth");
    	list.add("Something 1");
    	
    	assertTrue(5 == list.size());

    	assertThat(list.get(2), is("Third")); 
    	assertThat(list.get(0), is("First")); 
    	assertThat(list.getFirst(), is("First")); 
    	assertThat(list.getLast(), is("Something 1")); 
    	
    	assertThat(list.indexOf("Third"), is(2)); 
    	assertThat(list.lastIndexOf("Something 1"), is(4)); 
    	
    	while (list.size() != 0) {
    		list.remove();
    	}
    	assertTrue(0 == list.size());
    	
    	list.add("Second");
    	list.addFirst("First");
    	list.addLast("Third");
    	list.add("Fourth");
    	list.add("Something 1");
    	
    	assertTrue(5 == list.size());
    	
    	list.removeLast();
    	assertTrue(null == list.get(4));
    	
    	list.removeLast();
    	list.remove(2);
    	list.remove(1);
    	list.remove(0);
    	assertTrue(0 == list.size());
    	
    	
    	
    	list.clear();
    	assertTrue(0 == list.size());
    }
}
