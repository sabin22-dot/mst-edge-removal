package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
public class Main {

    public static void main(String[] args) {
        Graph graph = createGraph();
        List<Edge> mstEdges = KruskalMST.buildMST(graph);
        System.out.println("Original MST:");
        printEdges(mstEdges);
        System.out.println("Total weight: " + KruskalMST.totalWeight(mstEdges));
        System.out.println();

        Edge removedEdge = MSTUpdater.findEdgeInMst(mstEdges, 1, 2);
        System.out.println("Removing edge from MST: " + removedEdge);
        List<Edge> mstWithout = new ArrayList<>(mstEdges);
        mstWithout.remove(removedEdge);

        List<Set<Integer>> components = MSTUpdater.getComponents(mstWithout, graph.getVertices());
        System.out.println();
        System.out.println("Components after removing the edge:");
        int idx = 1;
        for (Set<Integer> comp : components) {
            System.out.println("Component " + idx + ": " + comp);
            idx++;
        }

        Edge replacement = MSTUpdater.findEdgeToReconnect(graph, mstEdges, graph.getVertices(), removedEdge);
        if (replacement == null) {
            System.out.println();
            System.out.println("No replacement edge found. The graph cannot be reconnected.");
            return;
        }

        System.out.println();
        System.out.println("Replacement edge selected: " + replacement);

        List<Edge> updatedMst = new ArrayList<>(mstWithout);
        updatedMst.add(replacement);

        Collections.sort(updatedMst);

        System.out.println();
        System.out.println("Updated MST after reconnection:");
        printEdges(updatedMst);
        System.out.println("Total weight: " + KruskalMST.totalWeight(updatedMst));
    }

    private static Graph createGraph() {
        Graph graph = new Graph(8);

        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 3);
        graph.addEdge(5, 7, 4);
        graph.addEdge(6, 7, 2);
        graph.addEdge(2, 6, 5);
        graph.addEdge(3, 6, 6);

        return graph;
    }

    private static void printEdges(List<Edge> edges) {
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
}
