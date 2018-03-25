package com.jackshenorion.dsaaj.graph.algograph;

import org.junit.Test;

import static org.junit.Assert.*;

public class NonWeightedAdjacencyMatrixGraphTest {
    @Test
    public void getInOutDegree() throws Exception {
        NonWeightedAdjacencyMatrixGraph<String> graph = new NonWeightedAdjacencyMatrixGraph<>(3);
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

    @Test
    public void getInverseGraph() {

        NonWeightedAdjacencyMatrixGraph<String> graph = new NonWeightedAdjacencyMatrixGraph<>(3);
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

        NonWeightedAdjacencyMatrixGraph<String> invertedGraph = graph.invert();

        assertTrue(invertedGraph.hasVertex(v1));
        assertTrue(invertedGraph.hasVertex(v2));
        assertTrue(invertedGraph.hasVertex(v3));
        assertTrue(invertedGraph.hasEdge(v2, v1));
        assertTrue(invertedGraph.hasEdge(v3, v1));
        assertTrue(invertedGraph.hasEdge(v1, v2));
        assertTrue(invertedGraph.hasEdge(v2, v3));
        assertTrue(!invertedGraph.hasEdge(v1, v3));
        assertTrue(!invertedGraph.hasEdge(v3, v2));
    }

    @Test
    public void testSquareGraph() {
        NonWeightedAdjacencyMatrixGraph<String> graph = new NonWeightedAdjacencyMatrixGraph(10);
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

        NonWeightedAdjacencyMatrixGraph<String> squareGraph = graph.square();

        assertTrue(squareGraph.hasVertex(v1));
        assertTrue(squareGraph.hasVertex(v2));
        assertTrue(squareGraph.hasVertex(v3));
        assertTrue(squareGraph.hasEdge(v1, v2));
        assertTrue(squareGraph.hasEdge(v3, v1));
        assertTrue(squareGraph.hasEdge(v2, v3));
        assertTrue(!squareGraph.hasEdge(v1, v3));
        assertTrue(!squareGraph.hasEdge(v3, v2));
        assertTrue(!squareGraph.hasEdge(v2, v1));
        assertTrue(!squareGraph.hasEdge(v3, v2));
    }

}