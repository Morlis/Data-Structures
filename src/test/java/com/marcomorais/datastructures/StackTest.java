package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class StackTest {
    @Test()
    public void test() throws Exception {
    	Stack<String> stack = new Stack<>();
    	
    	stack.push("First");
    	stack.push("Second");
    	stack.push("Third");
    	stack.push("Fourth");
    	stack.push("Something 1");
    	
    	assertTrue(5 == stack.size());

    	assertThat(stack.pop(), is("Something 1")); 
    	assertThat(stack.pop(), is("Fourth")); 
    	assertThat(stack.pop(), is("Third")); 
    	assertThat(stack.pop(), is("Second"));
    	
    	assertTrue(1 == stack.size());
    	
    	stack.clear();
    	assertTrue(0 == stack.size());
    }
}
