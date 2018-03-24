package com.jackshenorion.dsaaj.graph.algograph;

public interface IDirectedGraphAlgorithm<V> {
    int getInDegree(V v);
    int getOutDegree(V v);
}
