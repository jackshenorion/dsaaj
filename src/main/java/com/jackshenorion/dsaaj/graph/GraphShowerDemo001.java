package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.graph.algograph.NonWeightedAdjacencyListGraph;
import com.jackshenorion.dsaaj.graph.visualize.GraphVisualizer;

public class GraphShowerDemo001 {

    public static void main(String[] args) {
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

        new GraphVisualizer<>(graph.getGraphVisualInfo()).doShow();
    }
}
