package com.marcomorais.datastructures;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GraphTest {
    @Test()
    public void test() throws Exception {
    	Graph graph = new Graph();
	    graph.addVertex("Bob");
	    graph.addVertex("Alice");
	    graph.addVertex("Mark");
	    graph.addVertex("Rob");
	    graph.addVertex("Maria");
	    graph.addEdge("Bob", "Alice");
	    graph.addEdge("Bob", "Rob");
	    graph.addEdge("Alice", "Mark");
	    graph.addEdge("Rob", "Mark");
	    graph.addEdge("Alice", "Maria");
	    graph.addEdge("Rob", "Maria");
	    
	    System.out.println(graph.printGraph());
	    System.out.println("---------------------------");
	    
	    String depth = graph.depthFirstTraversal("Bob").toString();
	    System.out.println(depth);
	    assertEquals("[Bob, Rob, Maria, Alice, Mark]", depth);
	    
	    String breadth = graph.breadthFirstTraversal("Bob").toString();
	    System.out.println(breadth);
	    assertEquals("[Bob, Alice, Rob, Mark, Maria]", breadth);
	    
	    String unweighted = graph.unweightedShortPath("Maria", "Mark").toString();
	    System.out.println(unweighted);
	    assertThat(unweighted, anyOf(is("[Maria, Rob, Mark]"), is("[Maria, Alice, Mark]")));
	    
    }
}
