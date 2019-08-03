package com.axway.qainterview;

import org.junit.Assert;
import org.junit.Test;

public class GraphTraversalTest {
    @Test
    public void testPoC(){
        System.out.println("test are config and working");
    }
    @Test
    public void testTraversalSingleBranch(){

        Graph graph = new Graph();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");

        graph.addEdge("1", "5");
        graph.addEdge("2", "3");
        graph.addEdge("5", "4");
        graph.addEdge("4", "3");

        String[] traversal = GraphTraversal.depthFirstTraversal(graph, "5")
                .toArray(new String[0]);
       String[] expected = {"5", "4", "3", "2", "1"};

        Assert.assertArrayEquals(expected, traversal);
    }
    @Test
    public void testTraversalMultipleBranches(){

        Graph graph = new Graph();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");
        graph.addVertex("9");

        graph.addEdge("1", "5");
        graph.addEdge("2", "3");
        graph.addEdge("5", "4");
        graph.addEdge("4", "3");
        graph.addEdge("6", "5");
        graph.addEdge("6", "8");
        graph.addEdge("6", "9");
        graph.addEdge("7", "3");
        String[] traversal = GraphTraversal.depthFirstTraversal(graph, "4")
                .toArray(new String[0]);

        String[] expected = {"4", "3", "7", "2", "5","6","9","8","1"};

        Assert.assertArrayEquals(expected, traversal);
    }
}
