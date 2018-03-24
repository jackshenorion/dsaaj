package com.jackshenorion.dsaaj.graph.algograph;

public interface INoWeightAlgoGraph<V> {
    void addVertex(V v);
    void addEdge(V source, V target);
}
