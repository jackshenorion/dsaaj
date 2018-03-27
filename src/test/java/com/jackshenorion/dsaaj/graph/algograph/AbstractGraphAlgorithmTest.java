package com.jackshenorion.dsaaj.graph.algograph;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractGraphAlgorithmTest {

    @Test
    public void testAdjacencyListBFS () throws Exception {
        NonWeightedAdjacencyListGraph<String> graph1 = new NonWeightedAdjacencyListGraph();
        testGraphBFS(graph1);
    }

    @Test
    public void testAdjacencyMatrixBFS () throws Exception {
        NonWeightedAdjacencyMatrixGraph<String> graph1 = new NonWeightedAdjacencyMatrixGraph(10);
        testGraphBFS(graph1);
    }

    private void testGraphBFS(AbstractGraphAlgorithm<String> graph) {
        String v1 = "A";
        String v2 = "B";
        String v3 = "C";
        String v4 = "D";
        String v5 = "E";

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addEdge(v1, v2, 1);
        graph.addEdge(v1, v3, 1);
        graph.addEdge(v2, v1, 1);
        graph.addEdge(v3, v2, 1);
        graph.addEdge(v2, v4, 1);
        graph.addEdge(v3, v4, 1);
        graph.addEdge(v5, v1, 1);

        graph.bfs(v1, (v, weight) -> {
            System.out.print(v + " ");
            if (v == v1) {
                assertEquals(0, (int) weight);
            } else if (v == v2) {
                assertEquals(1, (int) weight);
            } else if (v == v3) {
                assertEquals(1, (int) weight);
            } else if (v == v4) {
                assertEquals(2, (int) weight);
            } else {
                assertTrue(false);
            }
        });

        System.out.println("");

        graph.bfs(v5, (v, weight) -> {
            System.out.print(v + " ");
            if (v == v1) {
                assertEquals(1, (int) weight);
            } else if (v == v2) {
                assertEquals(2, (int) weight);
            } else if (v == v3) {
                assertEquals(2, (int) weight);
            } else if (v == v4) {
                assertEquals(3, (int) weight);
            } else if (v == v5){
                assertEquals(0, (int) weight);
            }
        });

    }

}