package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class QueueTest {
    @Test()
    public void test() throws Exception {
    	Queue<String> queue = new Queue<>();
    	
    	queue.enqueue("First");
    	queue.enqueue("Second");
    	queue.enqueue("Third");
    	queue.enqueue("Fourth");
    	queue.enqueue("Something 1");
    	
    	assertTrue(5 == queue.size());

    	assertThat(queue.dequeue(), is("First")); 
    	assertThat(queue.dequeue(), is("Second")); 
    	assertThat(queue.dequeue(), is("Third")); 
    	assertThat(queue.dequeue(), is("Fourth"));
    	
    	assertTrue(1 == queue.size());
    	
    	queue.clear();
    	assertTrue(0 == queue.size());
    }
}
