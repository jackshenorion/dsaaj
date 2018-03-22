package com.jackshenorion.dsaaj.chapter9;

import com.google.common.collect.Lists;

import java.util.*;

public class AdjacentMatrixGraph<V, E> implements IGraph<V, E>, IGraphIterator {
    private Object[][] adjacentMatrix;
    private List<V> vertexes;
    private Map<V, Integer> vertexIndexes;
    private Set<E> edges;
    private Map<E, V> edgeSources;
    private Map<E, V> edgeTargets;

    public AdjacentMatrixGraph(int maxVertexCount) {
        adjacentMatrix = new Object[maxVertexCount][maxVertexCount];
        vertexes = new ArrayList<>();
        vertexIndexes = new HashMap<>();
        edges = new HashSet<>();
        edgeSources = new HashMap<>();
        edgeTargets = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        vertexes.add(v);
        vertexIndexes.put(v, vertexes.size() - 1);
    }

    @Override
    public void addEdge(V source, V target, E edge) {
        adjacentMatrix[vertexIndexes.get(source)][vertexIndexes.get(target)] = edge;
        edges.add(edge);
        edgeSources.put(edge, source);
        edgeTargets.put(edge, target);
    }

    @Override
    public List<V> getAllVertexes() {
        return Lists.newArrayList(vertexes);
    }

    @Override
    public Set<E> getAllEdges() {
        return new HashSet<>(edges);
    }

    @Override
    public V getEdgeSource(E e) {
        return edgeSources.get(e);
    }

    @Override
    public V getEdgeTarget(E e) {
        return edgeTargets.get(e);
    }


}
