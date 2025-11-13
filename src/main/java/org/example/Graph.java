package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Graph {

    private final int vertices;
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive");
        }
        this.vertices = vertices;
    }

    public int getVertices() {
        return vertices;
    }

    public void addEdge(int u, int v, int weight) {
        edges.add(new Edge(u, v, weight));
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }
}
