package com.jackshenorion.dsaaj.graph.algograph;

import java.util.HashMap;
import java.util.Map;

public class NonWeightedAdjacencyMatrixGraph<V> implements IDirectedGraphAlgorithm<V>, INonWeightedAlgoGraph<V> {
    private int[][] adjacencyMatrix;
    private Map<V, Integer> vertexToIndexMap;
    private Object[] indexToVertex;

    public NonWeightedAdjacencyMatrixGraph(int maxVertexCount) {
        adjacencyMatrix = new int[maxVertexCount][maxVertexCount];
        vertexToIndexMap = new HashMap<>();
        indexToVertex = new Object[maxVertexCount];
    }

    @Override
    public void addVertex(V v) {
        int index = vertexToIndexMap.size();
        vertexToIndexMap.put(v, index);
        indexToVertex[index] = v;
    }

    @Override
    public void addEdge(V source, V target) {
        adjacencyMatrix[vertexToIndexMap.get(source)][vertexToIndexMap.get(target)] = 1;
    }

    @Override
    public int getInDegree(V v) {
        int result = 0;
        int vIndex = vertexToIndexMap.get(v);
        for (int i = 0; i < vertexToIndexMap.size(); i++) {
            if (adjacencyMatrix[i][vIndex] > 0) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getOutDegree(V v) {
        int result = 0;
        int vIndex = vertexToIndexMap.get(v);
        for (int i = 0; i < vertexToIndexMap.size(); i++) {
            if (adjacencyMatrix[vIndex][i] > 0) {
                result++;
            }
        }
        return result;
    }

    public boolean hasVertex(V v) {
        return vertexToIndexMap.containsKey(v);
    }

    public boolean hasEdge(V source, V target) {
        return vertexToIndexMap.containsKey(source)
                && vertexToIndexMap.containsKey(target)
                && adjacencyMatrix[vertexToIndexMap.get(source)][vertexToIndexMap.get(target)] > 0;
    }

    public NonWeightedAdjacencyMatrixGraph<V> invert() {
        NonWeightedAdjacencyMatrixGraph<V> invertedGraph = new NonWeightedAdjacencyMatrixGraph<>(adjacencyMatrix.length);
        int vertexCount = vertexToIndexMap.size();
        for (Map.Entry<V, Integer> entry : vertexToIndexMap.entrySet()) {
            invertedGraph.addVertex(entry.getKey());
        }
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (hasEdge(i, j)) {
                    invertedGraph.addEdge((V) indexToVertex[j], (V) indexToVertex[i]);
                }
            }
        }
        return invertedGraph;
    }

    /*22.1-5*/
    public NonWeightedAdjacencyMatrixGraph<V> square() {
        NonWeightedAdjacencyMatrixGraph<V> squareGraph = new NonWeightedAdjacencyMatrixGraph<>(adjacencyMatrix.length);
        for (V v : vertexToIndexMap.keySet()) {
            squareGraph.addVertex(v);
        }
        for (int i = 0; i < vertexToIndexMap.size(); i++) {
            for (int j = 0; j < vertexToIndexMap.size(); j++) {
                if (hasEdge(i, j)) {
                    for (int k = 0; k < vertexToIndexMap.size(); k++) {
                        if (hasEdge(j, k)) {
                            squareGraph.addEdge((V) indexToVertex[i], (V) indexToVertex[k]);
                        }
                    }
                }
            }
        }
        return squareGraph;
    }

    private boolean hasEdge(int sourceIndex, int targetIndex) {
        return adjacencyMatrix[sourceIndex][targetIndex] > 0;
    }

}
