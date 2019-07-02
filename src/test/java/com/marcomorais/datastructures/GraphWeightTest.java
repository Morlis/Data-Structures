package com.marcomorais.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GraphWeightTest {
    @Test()
    public void test() throws Exception {
    	GraphNode nodeA = new GraphNode("A");
    	GraphNode nodeB = new GraphNode("B");
    	GraphNode nodeC = new GraphNode("C");
    	GraphNode nodeD = new GraphNode("D");
    	GraphNode nodeE = new GraphNode("E");
    	GraphNode nodeF = new GraphNode("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        GraphWeight graph = new GraphWeight();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        
        List<GraphNode> shortestPathForNodeB = Arrays.asList(nodeA);
        List<GraphNode> shortestPathForNodeC = Arrays.asList(nodeA);
        List<GraphNode> shortestPathForNodeD = Arrays.asList(nodeA, nodeB);
        List<GraphNode> shortestPathForNodeE = Arrays.asList(nodeA, nodeB, nodeD);
        List<GraphNode> shortestPathForNodeF = Arrays.asList(nodeA, nodeB, nodeD);
        
        String depth = graph.depthFirstTraversal(nodeA).toString();
	    System.out.println(depth);
	    assertEquals("[A, B, F, E, D, C]", depth);
	    
	    String breadth = graph.breadthFirstTraversal(nodeA).toString();
	    System.out.println(breadth);
	    assertEquals("[A, C, B, E, D, F]", breadth);
	    
	    String unweighted = graph.unweightedShortPath(nodeA, nodeE).toString();
	    System.out.println(unweighted);
	    assertEquals("[A, C, E]", unweighted);
	    
	    unweighted = graph.unweightedShortPath(nodeA, nodeF).toString();
	    System.out.println(unweighted);
	    assertEquals("[A, B, F]", unweighted);

	    graph = graph.calculateShortestPathFromSource(graph, nodeA);
	    
        for (GraphNode node : graph.getNodes()) {
        	System.out.println(node.getShortestPath().toString());
            switch (node.getName()) {
            case "B":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeB));
                break;
            case "C":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeC));
                break;
            case "D":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeD));
                break;
            case "E":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeE));
                break;
            case "F":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeF));
                break;
            }
        }
    }
}
