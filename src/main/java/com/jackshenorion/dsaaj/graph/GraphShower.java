package com.jackshenorion.dsaaj.graph;

import com.jackshenorion.dsaaj.circle.CircleCoordinateProvider;
import com.jackshenorion.dsaaj.circle.Coordinate;
import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IGraph;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

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
    private mxGraph graph = new mxGraph();

    public GraphShower(IGraph<V> graphData) throws HeadlessException {
        super("Graph Shower");
        this.graphData = graphData;
        init();
    }

    private void init() {
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        createStyle();
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
                if (seq % 3 == 0) {
                    ((mxCell) cell).setStyle("whiteVertex");
                } else if (seq % 3 == 1) {
                    ((mxCell) cell).setStyle("greyVertex");
                } else {
                    ((mxCell) cell).setStyle("blackVertex");
                }
                vertexToCell.put(v, cell);
                seq++;
            }

            int edgeSeq = 0;
            for (IEdge<V> edge : graphData.getAllEdges()) {
                if (edgeSeq % 3 == 0) {
                    graph.insertEdge(parent, null, edge.getWeight(), vertexToCell.get(edge.getSource()), vertexToCell.get(edge.getTarget()), "defEdge;directedEdge");
                } else if (edgeSeq % 3 == 1) {
                    graph.insertEdge(parent, null, edge.getWeight(), vertexToCell.get(edge.getSource()), vertexToCell.get(edge.getTarget()), "dashEdge;undirectedEdge");
                } else {
                    graph.insertEdge(parent, null, edge.getWeight(), vertexToCell.get(edge.getSource()), vertexToCell.get(edge.getTarget()), "boldEdge;directedEdge");
                }
                edgeSeq++;
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    private void createStyle() {
        Map<String, Object> whiteVertex = new HashMap<>();
        whiteVertex.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        whiteVertex.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        whiteVertex.put(mxConstants.STYLE_FONTCOLOR, "#000000");

        Map<String, Object> greyVertex = new HashMap<>();
        greyVertex.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        greyVertex.put(mxConstants.STYLE_FILLCOLOR, "grey");
        greyVertex.put(mxConstants.STYLE_FONTCOLOR, "black");

        Map<String, Object> blackVertex = new HashMap<>();
        blackVertex.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        blackVertex.put(mxConstants.STYLE_FILLCOLOR, "black");
        blackVertex.put(mxConstants.STYLE_FONTCOLOR, "white");

        Map<String, Object> defEdge = new HashMap<>();
        defEdge.put(mxConstants.STYLE_STROKEWIDTH, 1);
        defEdge.put(mxConstants.STYLE_STROKECOLOR, "black");
        defEdge.put(mxConstants.STYLE_DASHED, false);

        Map<String, Object> boldEdge = new HashMap<>();
        boldEdge.put(mxConstants.STYLE_STROKEWIDTH, 3);
        boldEdge.put(mxConstants.STYLE_STROKECOLOR, "black");
        boldEdge.put(mxConstants.STYLE_DASHED, false);

        Map<String, Object> dashEdge = new HashMap<>();
        dashEdge.put(mxConstants.STYLE_STROKEWIDTH, 1);
        dashEdge.put(mxConstants.STYLE_STROKECOLOR, "black");
        dashEdge.put(mxConstants.STYLE_DASHED, true);

        Map<String, Object> undirectedEdge = new HashMap<>();
        undirectedEdge.put(mxConstants.STYLE_ENDARROW, "none");

        Map<String, Object> directedEdge = new HashMap<>();
        directedEdge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);

        mxStylesheet stylesheet = graph.getStylesheet();
        stylesheet.putCellStyle("whiteVertex", whiteVertex);
        stylesheet.putCellStyle("greyVertex", greyVertex);
        stylesheet.putCellStyle("blackVertex", blackVertex);
        stylesheet.putCellStyle("defEdge", defEdge);
        stylesheet.putCellStyle("boldEdge", boldEdge);
        stylesheet.putCellStyle("dashEdge", dashEdge);
        stylesheet.putCellStyle("undirectedEdge", undirectedEdge);
        stylesheet.putCellStyle("directedEdge", directedEdge);
    }

    public void doShow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(maxX + itemWidth + offsetX, maxY + itemHeight + offsetY);
        setVisible(true);
    }
}
