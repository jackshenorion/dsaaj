package com.jackshenorion.dsaaj.tools;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class JGraphExample001 extends JFrame {

    public JGraphExample001() {
        super("Hello World!");
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        try {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30,"ROUNDED;strokeColor=red;fillColor=green");
            Object v2 = graph.insertVertex(parent, null, "World", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args) {
        JGraphExample001 helloWorld = new JGraphExample001();
        helloWorld.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        helloWorld.setSize(400, 320);
        helloWorld.setVisible(true);
    }
}
