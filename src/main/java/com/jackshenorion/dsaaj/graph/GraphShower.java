package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.circle.CircleCoordinateProvider;
import com.jackshenorion.dsaaj.circle.Coordinate;
import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IGraph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphShower<V> extends JFrame {

    private static final int itemWidth = 80;
    private static final int itemHeight = 30;
    private static final int offsetX = itemWidth;
    private static final int offsetY = itemHeight;

    private IGraph<V> graphData;
    private int maxX = 400;
    private int maxY = 320;

    public GraphShower(IGraph<V> graphData) throws HeadlessException {
        super("Graph Shower");
        this.graphData = graphData;
        init();
    }

    private void init() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
            Map<V, Object> vertexToCell = new HashMap<>();
            Collection<V> vertices = graphData.getAllVertexes();
            List<Coordinate> coordinateList = CircleCoordinateProvider.getCoordinates(vertices.size(), Math.max(itemHeight, itemWidth));
            int seq = 0;
            for (V v : vertices) {
                int thisX = (int) Math.round(coordinateList.get(seq).getX()) + offsetX;
                int thisY = (int) Math.round(coordinateList.get(seq).getY()) + offsetY;
                maxX = Math.max(thisX, maxX);
                maxY = Math.max(thisY, maxY);
                Object cell = graph.insertVertex(parent, null, v, thisX, thisY, itemWidth, itemHeight);
                vertexToCell.put(v, cell);
                seq++;
            }
            for (IEdge<V> edge : graphData.getAllEdges()) {
                graph.insertEdge(parent, null, edge.getWeight(), vertexToCell.get(edge.getSource()), vertexToCell.get(edge.getTarget()));
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public void doShow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(maxX + itemWidth + offsetX, maxY + itemHeight + offsetY);
        setVisible(true);
    }
}
