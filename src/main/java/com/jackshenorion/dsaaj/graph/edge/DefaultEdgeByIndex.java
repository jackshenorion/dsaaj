package com.jackshenorion.dsaaj.graph.edge;

import com.jackshenorion.dsaaj.graph.intf.IEdgeByIndex;

public class DefaultEdgeByIndex implements IEdgeByIndex {

    private int source;
    private int target;
    private double weight;

    public DefaultEdgeByIndex(final int source, final int target) {
        this(source, target, 1);
    }

    public DefaultEdgeByIndex(int source, int target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int getSource() {
        return source;
    }

    @Override
    public int getTarget() {
        return target;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
