package com.jackshenorion.dsaaj.graph.algograph;

import java.util.*;

public class AdjacencyMultiGraph<V> {

    private Map<V, List<V>> adjacencyLists;

    public AdjacencyMultiGraph() {
        adjacencyLists = new HashMap<>();
    }

    public void addVertex(V v) {
        adjacencyLists.put(v, new ArrayList<>());
    }

    public void addEdge(V v1, V v2) {
        adjacencyLists.get(v1).add(v2);
        if (v1 != v2) {
            adjacencyLists.get(v2).add(v1);
        }
    }

    public int edgeCount(V v1, V v2) {
        List<V> adjacencyList = adjacencyLists.get(v1);
        int result = 0;
        for (V v : adjacencyList) {
            if (v == v2) {
                result++;
            }
        }
        return result;
    }

    /*22.1-4*/
    public AdjacencyMultiGraph<V> simplify() {
        AdjacencyMultiGraph<V> newGraph = new AdjacencyMultiGraph();
        for (V v : adjacencyLists.keySet()) {
            newGraph.addVertex(v);
        }
        Set<V> processedVertices = new HashSet<>();
        for(Map.Entry<V, List<V>> entry : adjacencyLists.entrySet()) {
            V source = entry.getKey();
            processedVertices.add(source);
            Set<V> targetSet = new HashSet<>(entry.getValue());
            for (V target : targetSet) {
                if (!processedVertices.contains(target)) {
                    newGraph.addEdge(entry.getKey(), target);
                }
            }
        }
        return newGraph;
    }
}
