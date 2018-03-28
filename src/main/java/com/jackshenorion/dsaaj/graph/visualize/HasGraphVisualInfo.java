package com.jackshenorion.dsaaj.graph.visualize;

import com.jackshenorion.dsaaj.graph.intf.IEdgeByIndex;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface HasGraphVisualInfo<V> {
    List<V> getAllVertices();
    Collection<IEdgeByIndex> getAllEdges();
    int[] getVertexColors();
    Map<Integer, List<IEdgeByIndex>> getEdgesByType();
}
