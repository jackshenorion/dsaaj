package com.jackshenorion.dsaaj.graph.edge;

import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IHasValue;

public class WeightedEdge<V> implements IEdge<V>, IHasValue<Double> {

    private V source;
    private V target;
    private double value;

    public WeightedEdge() {
    }

    public WeightedEdge(V source, V target, double value) {
        this.source = source;
        this.target = target;
        this.value = value;
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
    public void setSource(V source) {
        this.source = source;
    }

    @Override
    public void setTarget(V target) {
        this.target = target;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
