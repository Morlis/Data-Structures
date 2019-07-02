package com.marcomorais.datastructures;

import java.util.Map.Entry;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphWeight {
	private Set<GraphNode> nodes = new HashSet<>();
	
	public void addNode(GraphNode nodeA) {
        nodes.add(nodeA);
    }
	
	public Set<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<GraphNode> nodes) {
        this.nodes = nodes;
    }
    
    public static GraphWeight calculateShortestPathFromSource(GraphWeight graph, GraphNode source) {
    	for(GraphNode n: graph.nodes) {
    		n.setDistance(Integer.MAX_VALUE);
    	}
    	
    	// To start set source node distance to zero
    	source.setDistance(0);
    	
    	Set<GraphNode> settledNodes = new HashSet<>();
    	Set<GraphNode> unsettledNodes = new HashSet<>();
    	
    	unsettledNodes.add(source);
    	
    	while(!unsettledNodes.isEmpty()) {
    		GraphNode currentNode = getLowestDistanceNode(unsettledNodes);
    		unsettledNodes.remove(currentNode);
    		
    		 for (Entry<GraphNode, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
    			 GraphNode adjacentNode = adjacencyPair.getKey();
    			 Integer edgeWeight = adjacencyPair.getValue();
    			 if (!settledNodes.contains(adjacentNode)) {
    				 calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
    				 unsettledNodes.add(adjacentNode);
    			 }
    			 
    		 }
    		
    		
    		settledNodes.add(currentNode);
    	}
    	
		return graph;
	}
    
    private static void calculateMinimumDistance(GraphNode evaluationNode, Integer edgeWeight, GraphNode sourceNode) {
    	Integer sourceDistance = sourceNode.getDistance();
    	
    	if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
    		evaluationNode.setDistance(sourceDistance + edgeWeight);
    		LinkedList<GraphNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
    		shortestPath.add(sourceNode);
    		evaluationNode.setShortestPath(shortestPath);
    	}
	}

	private static GraphNode getLowestDistanceNode(Set <GraphNode> unsettledNodes) {
    	GraphNode lowestDistanceNode = null;
    	int lowestDistance = Integer.MAX_VALUE;
    	
    	for(GraphNode node: unsettledNodes) {
    		if (node.getDistance() < lowestDistance) {
    			lowestDistance = node.getDistance();
    			lowestDistanceNode = node;
    		}
    	}
    	
    	return lowestDistanceNode;
    }
    
    public Set<GraphNode> depthFirstTraversal(GraphNode root) {
    	GraphNode node;
    	Set<GraphNode> visited = new LinkedHashSet<GraphNode>();
		Stack<GraphNode> stack = new Stack<GraphNode>();
		
		stack.push(root);
		while(!stack.isEmpty()) {
			try {
				node  = stack.pop();
				if (!visited.contains(node)) {
					visited.add(node);
					for(GraphNode adj: node.getAdjacentNodes().keySet()) {
						stack.push(adj);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return visited;
    }
    
    public Set<GraphNode> breadthFirstTraversal(GraphNode root) {
    	GraphNode node;
    	Set<GraphNode> visited = new LinkedHashSet<GraphNode>();
    	Queue<GraphNode> queue = new Queue<GraphNode>();
		
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			try {
				node  = queue.dequeue();
				if (!visited.contains(node)) {
					visited.add(node);
					for(GraphNode adj: node.getAdjacentNodes().keySet()) {
						queue.enqueue(adj);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return visited;
    }
    
    public List<GraphNode> unweightedShortPath(GraphNode start, GraphNode end) {
    	boolean found = false;
    	GraphNode node;
    	
    	Set<GraphNode> visited = new LinkedHashSet<GraphNode>();
    	Map<GraphNode, GraphNode> parent = new LinkedHashMap<>();
    	Queue<GraphNode> queue = new Queue<GraphNode>();
    	
    	for(GraphNode n: nodes) {
    		n.setDistance(Integer.MAX_VALUE);
    	}
    	
		queue.enqueue(start);
		start.setDistance(0);
		visited.add(start);
		parent.put(start, null);
		
		while(!queue.isEmpty() && !found) {
			try {
				node  = queue.dequeue();
				
				for(GraphNode n: node.getAdjacentNodes().keySet()) {
					if (!visited.contains(n)) {
						visited.add(n);
						n.setDistance(node.getDistance() + 1);
						parent.put(n, node);
						queue.enqueue(n);
						
						if (n.equals(end)) {
							found = true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		List<GraphNode> path = new LinkedList<GraphNode>(); 
		GraphNode crawl = end;
		while(crawl != null) {
			path.add(0, crawl);
			crawl = parent.get(crawl);
		}
		return path;
    }
    
    
}
