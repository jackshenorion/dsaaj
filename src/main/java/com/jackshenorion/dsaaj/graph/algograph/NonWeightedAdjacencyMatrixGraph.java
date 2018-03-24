package com.jackshenorion.dsaaj.graph.algograph;

import java.util.HashMap;
import java.util.Map;

public class NonWeightedAdjacencyMatrixGraph<V> implements IDirectedGraphAlgorithm<V>, INonWeightedAlgoGraph<V> {
    private int[][] adjcencyMatrix;
    private Map<V, Integer> vertices;

    public NonWeightedAdjacencyMatrixGraph(int maxVertexCount) {
        adjcencyMatrix = new int[maxVertexCount][maxVertexCount];
        vertices = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        vertices.put(v, vertices.size());
    }

    @Override
    public void addEdge(V source, V target) {
        adjcencyMatrix[vertices.get(source)][vertices.get(target)] = 1;
    }

    @Override
    public int getInDegree(V v) {
        int result = 0;
        int vIndex = vertices.get(v);
        for (int i = 0; i < vertices.size(); i++) {
            if (adjcencyMatrix[i][vIndex] > 0) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getOutDegree(V v) {
        int result = 0;
        int vIndex = vertices.get(v);
        for (int i = 0; i < vertices.size(); i++) {
            if (adjcencyMatrix[vIndex][i] > 0) {
                result++;
            }
        }
        return result;
    }

    public boolean hasVertex(V v) {
        return vertices.containsKey(v);
    }

    public boolean hasEdge(V source, V target) {
        return vertices.containsKey(source)
                && vertices.containsKey(target)
                && adjcencyMatrix[vertices.get(source)][vertices.get(target)] > 0;
    }

    public NonWeightedAdjacencyMatrixGraph<V> invert() {
        NonWeightedAdjacencyMatrixGraph<V> invertedGraph = new NonWeightedAdjacencyMatrixGraph<>(adjcencyMatrix.length);
        int vertexCount = vertices.size();
        Object[] vs = new Object[vertexCount];
        for (Map.Entry<V, Integer> entry: vertices.entrySet()) {
            invertedGraph.addVertex(entry.getKey());
            vs[entry.getValue()] = entry.getKey();
        }
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjcencyMatrix[i][j] > 0) {
                    invertedGraph.addEdge((V)vs[j], (V) vs[i]);
                }
            }
        }
        return invertedGraph;
    }
}
