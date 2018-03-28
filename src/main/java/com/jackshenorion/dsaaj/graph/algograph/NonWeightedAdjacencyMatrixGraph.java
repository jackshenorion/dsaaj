package com.jackshenorion.dsaaj.graph.algograph;

import com.jackshenorion.dsaaj.graph.edge.DefaultEdge;
import com.jackshenorion.dsaaj.graph.intf.IEdge;

import java.util.*;
import java.util.function.Consumer;

public class NonWeightedAdjacencyMatrixGraph<V>
        extends AbstractAlgoGraph<V>
        implements IDirectedGraphAlgorithm<V> {

    private int[][] adjacencyMatrix;

    public NonWeightedAdjacencyMatrixGraph(int maxVertexCount) {
        adjacencyMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(V v) {
        int newIndex = vertexToIndex.size();
        vertexToIndex.put(v, newIndex);
        indexToVertex.add(v);
    }

    public void addEdge(V source, V target) {
        adjacencyMatrix[vertexToIndex.get(source)][vertexToIndex.get(target)] = 1;
    }

    @Override
    public void addEdge(V source, V target, double weight) {
        addEdge(source, target);
    }

    @Override
    public Collection<V> getAllVertexes() {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public Collection<IEdge<V>> getAllEdges() {
        List<IEdge<V>> edges = new ArrayList<>();
        for (int i = 0; i < vertexToIndex.size(); i++) {
            for (int j = 0; j < vertexToIndex.size(); j++) {
                if ( adjacencyMatrix[i][j] > 0) {
                    edges.add(new DefaultEdge<V>((V)indexToVertex.get(i), (V)indexToVertex.get(j)));
                }
            }
        }
        return edges;
    }

    @Override
    public int getInDegree(V v) {
        int result = 0;
        int vIndex = vertexToIndex.get(v);
        for (int i = 0; i < vertexToIndex.size(); i++) {
            if (adjacencyMatrix[i][vIndex] > 0) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getOutDegree(V v) {
        int result = 0;
        int vIndex = vertexToIndex.get(v);
        for (int i = 0; i < vertexToIndex.size(); i++) {
            if (adjacencyMatrix[vIndex][i] > 0) {
                result++;
            }
        }
        return result;
    }

    public boolean hasVertex(V v) {
        return vertexToIndex.containsKey(v);
    }

    public boolean hasEdge(V source, V target) {
        return vertexToIndex.containsKey(source)
                && vertexToIndex.containsKey(target)
                && adjacencyMatrix[vertexToIndex.get(source)][vertexToIndex.get(target)] > 0;
    }

    public NonWeightedAdjacencyMatrixGraph<V> invert() {
        NonWeightedAdjacencyMatrixGraph<V> invertedGraph = new NonWeightedAdjacencyMatrixGraph<>(adjacencyMatrix.length);
        int vertexCount = vertexToIndex.size();
        for (Map.Entry<V, Integer> entry : vertexToIndex.entrySet()) {
            invertedGraph.addVertex(entry.getKey());
        }
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (hasEdge(i, j)) {
                    invertedGraph.addEdge((V) indexToVertex.get(j), (V) indexToVertex.get(i));
                }
            }
        }
        return invertedGraph;
    }

    /*22.1-5*/
    public NonWeightedAdjacencyMatrixGraph<V> square() {
        NonWeightedAdjacencyMatrixGraph<V> squareGraph = new NonWeightedAdjacencyMatrixGraph<>(adjacencyMatrix.length);
        for (V v : vertexToIndex.keySet()) {
            squareGraph.addVertex(v);
        }
        for (int i = 0; i < vertexToIndex.size(); i++) {
            for (int j = 0; j < vertexToIndex.size(); j++) {
                if (hasEdge(i, j)) {
                    for (int k = 0; k < vertexToIndex.size(); k++) {
                        if (hasEdge(j, k)) {
                            squareGraph.addEdge((V) indexToVertex.get(i), (V) indexToVertex.get(k));
                        }
                    }
                }
            }
        }
        return squareGraph;
    }

    /*22.1-6*/
    public boolean hasUniversalSink() {
        int i = 0;
        int j = 1;
        while (j < vertexToIndex.size()) {
            i = hasEdge(i, j) ? j : i; // winner is the target which probably is sinker
            j = j + 1; // next
        }
        int winner = i;
        for (int k = 0; k < vertexToIndex.size(); k++) {
            if (winner != k && (adjacencyMatrix[k][winner] == 0 || adjacencyMatrix[winner][k] > 0)) {
                return false; // real sinker should be all vertices' target and should not be anyone's source
            }
        }
        return true;
    }

    private boolean hasEdge(int sourceIndex, int targetIndex) {
        return adjacencyMatrix[sourceIndex][targetIndex] > 0;
    }

    @Override
    protected void forEachAdjacentVertex(int uIndex, Consumer<Integer> adjacentVertexConsumer) {
        for (int i = 0 ; i < getVertexCount(); i ++) {
            if (adjacencyMatrix[uIndex][i] > 0) {
                adjacentVertexConsumer.accept(i);
            }
        }
    }
}
