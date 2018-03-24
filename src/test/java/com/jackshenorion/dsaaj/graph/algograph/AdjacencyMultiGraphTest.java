package com.jackshenorion.dsaaj.graph.algograph;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyMultiGraphTest {

    @Test
    public void testSimplifyMultiGraph() {
        AdjacencyMultiGraph<String> multiGraph = new AdjacencyMultiGraph<>();
        String v1 = "A";
        String v2 = "B";
        String v3 = "C";

        multiGraph.addVertex(v1);
        multiGraph.addVertex(v2);
        multiGraph.addVertex(v3);

        multiGraph.addEdge(v1, v1);
        multiGraph.addEdge(v1, v2);
        multiGraph.addEdge(v1, v2);
        multiGraph.addEdge(v2, v3);
        multiGraph.addEdge(v3, v3);
        multiGraph.addEdge(v3, v3);

        assertEquals(1, multiGraph.edgeCount(v1, v1));
        assertEquals(2, multiGraph.edgeCount(v1, v2));
        assertEquals(1, multiGraph.edgeCount(v2, v3));
        assertEquals(2, multiGraph.edgeCount(v3, v3));

        AdjacencyMultiGraph<String> simplifiedGraph = multiGraph.simplify();

        assertEquals(0, simplifiedGraph.edgeCount(v1, v1));
        assertEquals(0, simplifiedGraph.edgeCount(v1, v1));
        assertEquals(0, simplifiedGraph.edgeCount(v1, v1));
        assertEquals(1, simplifiedGraph.edgeCount(v1, v2));
        assertEquals(1, simplifiedGraph.edgeCount(v2, v3));
        assertEquals(0, simplifiedGraph.edgeCount(v1, v3));
    }

}