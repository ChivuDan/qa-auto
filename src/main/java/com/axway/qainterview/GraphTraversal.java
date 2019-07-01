package com.axway.qainterview;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

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

}
