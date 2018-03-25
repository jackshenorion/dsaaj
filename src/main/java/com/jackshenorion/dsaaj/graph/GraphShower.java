package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IGraph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphShower<V> extends JFrame {

    private IGraph<V> graphData;

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
            int x = 10;
            int y = 10;
            int space = 50;
            Map<V, Object> vertexToCell = new HashMap<>();
            for (V v : graphData.getAllVertexes()) {
                Object cell = graph.insertVertex(parent, null, v, x, y, 80, 30);
                vertexToCell.put(v, cell);
                x += space;
                y += space;
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
        setSize(400, 320);
        setVisible(true);
    }
}
