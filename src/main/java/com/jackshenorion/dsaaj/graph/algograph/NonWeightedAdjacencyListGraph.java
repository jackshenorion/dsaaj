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
}
