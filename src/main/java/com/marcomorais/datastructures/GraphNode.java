package com.marcomorais.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class GraphNode {
	private String name;
    private LinkedList<GraphNode> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<GraphNode, Integer> adjacentNodes = new HashMap<>();

    public GraphNode(String name) {
        this.name = name;
    }

    public void addDestination(GraphNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<GraphNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<GraphNode, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<GraphNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<GraphNode> shortestPath) {
        this.shortestPath = shortestPath;
    }
    
    @Override
    public String toString() {
    	return name;
    }
}
