package com.jackshenorion.dsaaj.graph.algograph;

import com.jackshenorion.dsaaj.graph.edge.DefaultEdge;
import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IGraph;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NonWeightedAdjacencyListGraph<V> implements IGraph<V>, IDirectedGraphAlgorithm<V> {
    private static final int DEFAULT_ADJACENCY_LIST_LEN = 1;

    private Map<V, List<V>> adjacencyLists;

    public NonWeightedAdjacencyListGraph() {
        adjacencyLists = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        adjacencyLists.put(v, new ArrayList<>(DEFAULT_ADJACENCY_LIST_LEN));
    }


    public void addEdge(V source, V target) {
        assert adjacencyLists.containsKey(source);
        adjacencyLists.get(source).add(target);
    }

    @Override
    public void addEdge(V source, V target, double weight) {
        assert adjacencyLists.containsKey(source);
        adjacencyLists.get(source).add(target);
    }

    @Override
    public Collection<V> getAllVertexes() {
        return new ArrayList<>(adjacencyLists.keySet());
    }

    @Override
    public Collection<IEdge<V>> getAllEdges() {
        List<IEdge<V>> edges = new ArrayList<>();
        for (Map.Entry<V, List<V>> entry : adjacencyLists.entrySet()) {
            V source = entry.getKey();
            for (V target : entry.getValue()) {
                edges.add(new DefaultEdge<>(source, target));
            }
        }
        return edges;
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

    /*BFS*/
    public void bfs(V root, BiConsumer<V, Integer> onVertex) {
        Queue<V> queue = new LinkedList<>();
        Map<V, Integer> foundVertices = new HashMap<>();
        queue.add(root);
        foundVertices.put(root, 0);
        onVertex.accept(root, 0);
        while (queue.size() > 0) {
            V u = queue.poll();
            int k = foundVertices.get(u);
            for (V v : adjacencyLists.get(u)) {
                if (!foundVertices.containsKey(v)) {
                    queue.add(v);
                    foundVertices.put(v, k + 1);
                    onVertex.accept(v, k + 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "NonWeightedAdjacencyListGraph{" +
                "adjacencyLists=" + adjacencyLists +
                '}';
    }
}
