package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.graph.algograph.NonWeightedAdjacencyListGraph;
import com.jackshenorion.dsaaj.graph.visualize.GraphVisualizer;

public class GraphShowerDemo001 {

    public static void main(String[] args) {
        NonWeightedAdjacencyListGraph<String> graph = new NonWeightedAdjacencyListGraph();
        String r = "r";
        String s = "s";
        String t = "t";
        String u = "u";
        String v = "v";
        String w = "w";
        String x = "x";
        String y = "y";

        graph.addVertex(u);
        graph.addVertex(y);
        graph.addVertex(x);
        graph.addVertex(w);
        graph.addVertex(v);
        graph.addVertex(r);
        graph.addVertex(s);
        graph.addVertex(t);

        graph.addEdge(r, s);
        graph.addEdge(r, v);
        graph.addEdge(s, w);
        graph.addEdge(w, t);
        graph.addEdge(w, x);
        graph.addEdge(t, x);
        graph.addEdge(t, u);
        graph.addEdge(x, u);
        graph.addEdge(x, y);
        graph.addEdge(u, y);

        graph.addEdge(s, r);
        graph.addEdge(v, r);
        graph.addEdge(w, s);
        graph.addEdge(t, w);
        graph.addEdge(x, w);
        graph.addEdge(x, t);
        graph.addEdge(u, t);
        graph.addEdge(u, x);
        graph.addEdge(y, x);
        graph.addEdge(y, u);

        GraphVisualizer<String> graphVisualizer = new GraphVisualizer<>(graph.getGraphVisualInfo());
        graphVisualizer.run(n -> graph.bfs(s, graphVisualizer::onGraphChange));
    }
}
