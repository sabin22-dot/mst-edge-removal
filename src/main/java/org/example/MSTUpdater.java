package org.example;
import java.util.*;

public class MSTUpdater {
    public static Edge findEdgeInMst(List<Edge> mstEdges, int u, int v) {
        for (Edge e : mstEdges) {
            if ((e.getU() == u && e.getV() == v) || (e.getU() == v && e.getV() == u)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Requested edge (" + u + "," + v + ") is not in the MST.");
    }

    public static List<Set<Integer>> getComponents(List<Edge> mstEdges, int vertices) {
        Map<Integer, List<Integer>> adj = buildAdjacency(mstEdges, vertices);
        boolean[] visited = new boolean[vertices];
        List<Set<Integer>> components = new ArrayList<>();

        for (int start = 0; start < vertices; start++) {
            if (!visited[start]) {
                Set<Integer> comp = new LinkedHashSet<>();
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(start);
                visited[start] = true;

                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    comp.add(u);
                    for (int v : adj.get(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            stack.push(v);
                        }
                    }
                }
                components.add(comp);
            }
        }
        return components;
    }
    private static Map<Integer, List<Integer>> buildAdjacency(List<Edge> edges, int vertices) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.getU()).add(e.getV());
            adj.get(e.getV()).add(e.getU());
        }
        return adj;
    }
    public static Edge findEdgeToReconnect(Graph graph, List<Edge> mstEdges, int vertices, Edge removedEdge) {
        List<Edge> mstWithout = new ArrayList<>(mstEdges);
        if (!mstWithout.remove(removedEdge)) {
            throw new IllegalArgumentException("The removed edge is not part of the MST.");
        }
        boolean[] inFirstComponent = new boolean[vertices];
        markComponent(mstWithout, vertices, removedEdge.getU(), inFirstComponent);
        Edge best = null;
        for (Edge e : graph.getEdges()) {
            if (isSameUndirectedEdge(e, removedEdge)) {
                continue;
            }
            int u = e.getU();
            int v = e.getV();

            if (inFirstComponent[u] != inFirstComponent[v]) { // edge crosses the cut
                if (best == null || e.getWeight() < best.getWeight()) {
                    best = e;
                }
            }
        }

        return best;
    }
    private static void markComponent(List<Edge> edges, int vertices, int start, boolean[] inComponent) {
        Map<Integer, List<Integer>> adj = buildAdjacency(edges, vertices);
        Arrays.fill(inComponent, false);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        inComponent[start] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : adj.get(u)) {
                if (!inComponent[v]) {
                    inComponent[v] = true;
                    stack.push(v);
                }
            }
        }
    }
    private static boolean isSameUndirectedEdge(Edge a, Edge b) {
        return (a.getWeight() == b.getWeight()) &&
                ((a.getU() == b.getU() && a.getV() == b.getV()) ||
                 (a.getU() == b.getV() && a.getV() == b.getU()));
    }
}
