package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.graph.intf.IGraph;
import org.jgrapht.graph.DirectedMultigraph;

public class MyJGraphTAdapter<V, E> extends DirectedMultigraph<V, E> {

    public MyJGraphTAdapter(IGraph<V, E> graph, Class<E> edgeClass) {
        super(edgeClass);
        init(graph);
    }

    private void init(IGraph<V, E> graph) {
        graph.getAllVertexes().forEach(v -> addVertex(v));
        for (E e : graph.getAllEdges()) {
            addEdge(graph.getEdgeSource(e), graph.getEdgeTarget(e), e);
        }
    }
}
