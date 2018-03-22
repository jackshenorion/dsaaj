package com.jackshenorion.dsaaj.graph.intf;

public interface IEdge<V> {
    V getSource();
    V getTarget();
    void setSource(V source);
    void setTarget(V target);
}
