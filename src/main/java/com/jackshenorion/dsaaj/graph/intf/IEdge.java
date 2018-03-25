package com.jackshenorion.dsaaj.graph.intf;

public interface IEdge<V> {
    V getSource();
    V getTarget();
    double getWeight();
}
