package com.axway.qainterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;
@SpringBootApplication

public class Main {

    public static void main(String[] args) {



                SpringApplication.run(Main.class, args);


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

        Set<String> traversal = GraphTraversal.breadthFirstTraversal(graph, "1");
        for (String key : traversal) {
            System.out.println(key);
        }
    }

}
