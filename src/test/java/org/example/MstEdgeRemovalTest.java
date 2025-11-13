package org.example;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MstEdgeRemovalTest {

    private Graph buildExampleGraph() {
        Graph graph = new Graph(8); // vertices 0..7

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

    @Test
    public void testMstHasCorrectNumberOfEdges() {
        Graph graph = buildExampleGraph();
        List<Edge> mst = KruskalMST.buildMST(graph);
        assertEquals(graph.getVertices() - 1, mst.size(),
                "MST must have V-1 edges");
    }

    @Test
    public void testMstIsAcyclic() {
        Graph graph = buildExampleGraph();
        List<Edge> mst = KruskalMST.buildMST(graph);

        UnionFind uf = new UnionFind(graph.getVertices());
        for (Edge e : mst) {
            boolean merged = uf.union(e.getU(), e.getV());
            assertTrue(merged, "MST should not contain cycles");
        }
    }

    @Test
    public void testReplacementEdgeExistsAndReconnects() {
        Graph graph = buildExampleGraph();
        List<Edge> mst = KruskalMST.buildMST(graph);

        Edge removed = MSTUpdater.findEdgeInMst(mst, 1, 2);
        Edge replacement = MSTUpdater.findEdgeToReconnect(graph, mst, graph.getVertices(), removed);
        assertNotNull(replacement, "Replacement edge must exist");

        List<Edge> updated = new ArrayList<>(mst);
        updated.remove(removed);
        updated.add(replacement);
        assertEquals(graph.getVertices() - 1, updated.size());

        UnionFind uf = new UnionFind(graph.getVertices());
        for (Edge e : updated) {
            boolean merged = uf.union(e.getU(), e.getV());
            assertTrue(merged, "Updated MST should not contain cycles");
        }
    }
}
