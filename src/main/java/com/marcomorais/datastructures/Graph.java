package com.marcomorais.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	private Map<Vertex, List<Vertex>> vertices;
	
	public Graph() {
		vertices = new HashMap<Vertex, List<Vertex>>();
	}
	
	public void addVertex(final String label) {
		vertices.putIfAbsent(new Vertex(label), new ArrayList<Vertex>());
	}
	
	public void removeVertex(final String label) {
		Vertex v = new Vertex(label);
		
		vertices.values().stream().forEach((edge) -> edge.remove(v));
		vertices.remove(v);
	}
	
	public void addEdge(final String label1, final String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		
		vertices.get(v1).add(v2);
		vertices.get(v2).add(v1);
	}
	
	public void removeEdge(final String label1, final String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		
		List<Vertex> edg1 = vertices.get(v1);
		List<Vertex> edg2 = vertices.get(v2);
		
		if (edg1 != null) {
			edg1.remove(v2);
		}
		
		if (edg2 != null) {
			edg2.remove(v1);
		}
	}
	
	public Set<String> depthFirstTraversal(String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Stack<String> stack = new Stack<String>();
		
		stack.push(root);
		while(!stack.isEmpty()) {
			try {
				String vertex = stack.pop();
				if (!visited.contains(vertex)) {
					visited.add(vertex);
					for (Vertex v: getAdjVertices(vertex)) {
						stack.push(v.label);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return visited;
	}
	
	public Set<String> breadthFirstTraversal(String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Queue<String> queue = new Queue<String>();
		
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			try {
				String vertex = queue.dequeue();
				if (!visited.contains(vertex)) {
					visited.add(vertex);
					for (Vertex v: getAdjVertices(vertex)) {
						queue.enqueue(v.label);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return visited;
	}
	
	/**
	 *  Shortest path between two vertices of unweighted graph 
	 */
	public List<String> unweightedShortPath(String source, String dest) {
		boolean found = false;
		Set<String> visited = new LinkedHashSet<String>();
		Map<String, String> parent = new LinkedHashMap<String, String>();
		Map<String, Integer> distance = new LinkedHashMap<String, Integer>();
		Queue<String> queue = new Queue<String>();
		
		for (Vertex v : vertices.keySet()) {
			parent.put(v.label, null);
			distance.put(v.label, Integer.MAX_VALUE);
		}

		queue.enqueue(source);
		distance.put(source, 0);
		visited.add(source);

		while(!queue.isEmpty() && !found) {
			try {
				String vertex = queue.dequeue();

				for (Vertex v: getAdjVertices(vertex)) {
					if (!visited.contains(v.label)) {
						visited.add(v.label);
						distance.put(v.label, distance.get(vertex) + 1);
						parent.put(v.label, vertex);
						queue.enqueue(v.label);
						
						if (v.label.equals(dest)) {
							found = true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<String> path = new LinkedList<String>();
		if (found) {
			String crawl = dest;
			path.add(0, crawl);
			
			while(parent.get(crawl) != null) {
				path.add(0, parent.get(crawl));
				crawl = parent.get(crawl);
			}
		}
		return path;
	}

	List<Vertex> getAdjVertices(String label) {
        return vertices.get(new Vertex(label));
    }
	
	String printGraph() {
		StringBuffer sb = new StringBuffer();
		
		for (Vertex v: vertices.keySet()) {
			sb.append(v);
			sb.append(vertices.get(v));
			sb.append('\n');
		}
		return sb.toString();
	}
}
