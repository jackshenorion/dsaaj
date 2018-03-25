package com.jackshenorion.dsaaj.graph.intf;

import java.util.Collection;

public interface IGraph<V> {
    void addVertex(V v);
    void addEdge(V source, V target, double weight);
    Collection<V> getAllVertexes();
    Collection<IEdge<V>> getAllEdges();
}
