package com.jackshenorion.dsaaj.graph.algograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonWeightedAdjacencyListGraph<V> implements INonWeightedAlgoGraph<V>, IDirectedGraphAlgorithm<V> {
    private static final int DEFAULT_ADJACENCY_LIST_LEN = 1;

    private Map<V, List<V>> adjacencyLists;

    public NonWeightedAdjacencyListGraph() {
        adjacencyLists = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        adjacencyLists.put(v, new ArrayList<>(DEFAULT_ADJACENCY_LIST_LEN));
    }

    @Override
    public void addEdge(V source, V target) {
        assert adjacencyLists.containsKey(source);
        adjacencyLists.get(source).add(target);
    }

    @Override
    public int getInDegree(V v) {
        int result = 0;
        for (List<V> adjacencyList : adjacencyLists.values()) {
            for (V vertex : adjacencyList) {
                if (vertex == v) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getOutDegree(V v) {
        return adjacencyLists.get(v).size();
    }

    public boolean hasVertex(V v) {
        return adjacencyLists.containsKey(v);
    }

    public boolean hasEdge(V source, V target) {
        return adjacencyLists.containsKey(source) && adjacencyLists.get(source).contains(target);
    }

    public NonWeightedAdjacencyListGraph<V> invert() {
        NonWeightedAdjacencyListGraph<V> invertedGraph = new NonWeightedAdjacencyListGraph<>();
        for (V v : adjacencyLists.keySet()) {
            invertedGraph.addVertex(v);
        }
        for (Map.Entry<V, List<V>> entry : adjacencyLists.entrySet()) {
            for (V newSource : entry.getValue()) {
                invertedGraph.addEdge(newSource, entry.getKey());
            }
        }
        return invertedGraph;
    }

    /*22.1-5*/
    public NonWeightedAdjacencyListGraph<V> square() {
        NonWeightedAdjacencyListGraph<V> squareGraph = new NonWeightedAdjacencyListGraph<>();
        for (V v : adjacencyLists.keySet()) {
            squareGraph.addVertex(v);
        }
        for (Map.Entry<V, List<V>> entry : adjacencyLists.entrySet()) {
            for (V middleVertex : entry.getValue()) {
                for (V target : adjacencyLists.get(middleVertex)) {
                    if (entry.getKey() != target) {
                        squareGraph.addEdge(entry.getKey(), target);
                    }
                }
            }
        }
        return squareGraph;
    }

    @Override
    public String toString() {
        return "NonWeightedAdjacencyListGraph{" +
                "adjacencyLists=" + adjacencyLists +
                '}';
    }
}
