package com.axway.qainterview;

import java.util.List;

public class GraphDTO {
    private List<String> vertices;
    private List<Edge> edges;

    public List<String> getVertices() {
        return vertices;
    }

    public void setVertices(List<String> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
