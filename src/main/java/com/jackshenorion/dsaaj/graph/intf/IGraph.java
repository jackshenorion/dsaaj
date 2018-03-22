package com.jackshenorion.dsaaj.graph.intf;

import java.util.List;
import java.util.Set;

public interface IGraph<V, E> {
    void addVertex(V v);

    void addEdge(V source, V target, E edge);

    List<V> getAllVertexes();

    Set<E> getAllEdges();

    V getEdgeSource(E e);

    V getEdgeTarget(E e);
}
