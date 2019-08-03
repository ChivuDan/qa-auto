package com.axway.qainterview;

import java.util.*;

public class GraphTraversal {

	public static Set<String> breadthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
				if (!visited.contains(v.label)) {
					visited.add(v.label);
					queue.add(v.label);
				}
			}
		}
		return visited;
	}

	public static Set<String> depthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		Stack<String> stack = new Stack<>();
		stack.push(root);
		while(!stack.empty()) {
			String next = stack.pop();
			visited.add(next);
			for (Graph.Vertex adjVertex : graph.getAdjVertices(next)) {
				String adjLabel = adjVertex.toString();
				if (!visited.contains(adjLabel)) {
					stack.push(adjLabel);
				}
			}
		}
		return visited;
	}
}
