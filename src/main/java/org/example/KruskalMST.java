package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class KruskalMST {

    public static List<Edge> buildMST(Graph graph) {
        List<Edge> allEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(allEdges);

        UnionFind uf = new UnionFind(graph.getVertices());
        List<Edge> mst = new ArrayList<>();

        for (Edge e : allEdges) {
            if (uf.union(e.getU(), e.getV())) {
                mst.add(e);
            }
        }

        if (mst.size() != graph.getVertices() - 1) {
            throw new IllegalStateException("Graph is not connected, MST does not exist.");
        }

        return mst;
    }

    public static int totalWeight(List<Edge> edges) {
        int sum = 0;
        for (Edge e : edges) {
            sum += e.getWeight();
        }
        return sum;
    }
}
