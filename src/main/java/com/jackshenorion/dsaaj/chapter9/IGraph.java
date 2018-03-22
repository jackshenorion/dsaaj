package com.jackshenorion.dsaaj.chapter9;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IGraph<V, E> {
    void addVertex(V v);

    void addEdge(V source, V target, E edge);

    List<V> getAllVertexes();

    Set<E> getAllEdges();

    V getEdgeSource(E e);

    V getEdgeTarget(E e);
}
