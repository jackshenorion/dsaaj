package com.jackshenorion.dsaaj.graph.algograph;

import org.junit.Test;

import static org.junit.Assert.*;

public class NonWeightedAdjacencyListGraphTest {
    @Test
    public void getInOutDegree() throws Exception {

        NonWeightedAdjacencyListGraph<String> graph = new NonWeightedAdjacencyListGraph();
        String v1 = "A";
        String v2 = "B";
        String v3 = "C";
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v1);
        graph.addEdge(v3, v2);

        assertEquals(1, graph.getInDegree(v1));
        assertEquals(2, graph.getInDegree(v2));
        assertEquals(1, graph.getInDegree(v3));

        assertEquals(2, graph.getOutDegree(v1));
        assertEquals(1, graph.getOutDegree(v2));
        assertEquals(1, graph.getOutDegree(v3));
    }

}