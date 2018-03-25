package com.jackshenorion.dsaaj.graph.edge;

import com.jackshenorion.dsaaj.graph.intf.IEdge;

public class DefaultEdge<V> implements IEdge<V> {

    private V source;
    private V target;
    private double weight;

    public DefaultEdge(V source, V target) {
        this(source, target, 1);
    }

    public DefaultEdge(V source, V target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public V getSource() {
        return source;
    }

    @Override
    public V getTarget() {
        return target;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
