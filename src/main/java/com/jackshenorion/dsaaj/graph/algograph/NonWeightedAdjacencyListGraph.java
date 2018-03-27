package com.jackshenorion.dsaaj.graph.algograph;

import com.jackshenorion.dsaaj.graph.edge.DefaultEdge;
import com.jackshenorion.dsaaj.graph.intf.IEdge;
import com.jackshenorion.dsaaj.graph.intf.IGraph;

import java.util.*;
import java.util.function.BiConsumer;

public class NonWeightedAdjacencyListGraph<V> implements IGraph<V>, IDirectedGraphAlgorithm<V> {
    private static final int DEFAULT_ADJACENCY_LIST_LEN = 1;

    private List<List<Integer>> adjacencyLists;
    private Map<V, Integer> vertexToIndex;
    private List<V> indexToVertex;

    public NonWeightedAdjacencyListGraph() {
        adjacencyLists = new ArrayList<>();
        vertexToIndex = new HashMap<>();
        indexToVertex = new ArrayList<>();
    }

    @Override
    public void addVertex(V v) {
        int newIndex = indexToVertex.size();
        vertexToIndex.put(v, newIndex);
        indexToVertex.add(v);
        adjacencyLists.add(new ArrayList<>(DEFAULT_ADJACENCY_LIST_LEN));
    }


    public void addEdge(V source, V target) {
        adjacencyLists.get(vertexToIndex.get(source)).add(vertexToIndex.get(target));
    }

    @Override
    public void addEdge(V source, V target, double weight) {
        addEdge(source, target);
    }

    @Override
    public Collection<V> getAllVertexes() {
        return new ArrayList<>(indexToVertex);
    }

    @Override
    public Collection<IEdge<V>> getAllEdges() {
        List<IEdge<V>> edges = new ArrayList<>();
        for (int i = 0; i < adjacencyLists.size(); i++) {
            int sourceIndex = i;
            for (Integer targetIndex : adjacencyLists.get(sourceIndex)) {
                edges.add(new DefaultEdge<>(indexToVertex.get(sourceIndex), indexToVertex.get(targetIndex)));
            }
        }
        return edges;
    }

    @Override
    public int getInDegree(V v) {
        int result = 0;
        int vIndex = vertexToIndex.get(v);
        for (List<Integer> adjacencyList : adjacencyLists) {
            for (Integer target : adjacencyList) {
                if (target == vIndex) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getOutDegree(V v) {
        return adjacencyLists.get(vertexToIndex.get(v)).size();
    }

    public boolean hasVertex(V v) {
        return vertexToIndex.containsKey(v);
    }

    public boolean hasEdge(V source, V target) {
        return vertexToIndex.containsKey(source) && adjacencyLists.get(vertexToIndex.get(source)).contains(vertexToIndex.get(target));
    }

    public NonWeightedAdjacencyListGraph<V> invert() {
        NonWeightedAdjacencyListGraph<V> invertedGraph = new NonWeightedAdjacencyListGraph<>();
        for (V v : indexToVertex) {
            invertedGraph.addVertex(v);
        }
        for (int i = 0; i < adjacencyLists.size(); i++) {
            for (int newSourceIndex : adjacencyLists.get(i)) {
                invertedGraph.addEdge(indexToVertex.get(newSourceIndex), indexToVertex.get(i));
            }
        }
        return invertedGraph;
    }

    /*22.1-5*/
    public NonWeightedAdjacencyListGraph<V> square() {
        NonWeightedAdjacencyListGraph<V> squareGraph = new NonWeightedAdjacencyListGraph<>();
        for (V v : indexToVertex) {
            squareGraph.addVertex(v);
        }
        for (int i = 0; i < adjacencyLists.size(); i++) {
            for (int middleVertexIndex : adjacencyLists.get(i)) {
                for (int targetIndex : adjacencyLists.get(middleVertexIndex)) {
                    if (i != targetIndex) {
                        squareGraph.addEdge(indexToVertex.get(i), indexToVertex.get(targetIndex));
                    }
                }
            }
        }
        return squareGraph;
    }

    /*BFS*/
    public GraphTraverseInfo bfs(V root, BiConsumer<V, Integer> onVertex) {

        int rootIndex = vertexToIndex.get(root);

        int vCount = indexToVertex.size();
        int[] colors = new int[vCount]; // 0: white; 1: grey; 2: black
        int[] parents = new int[vCount];
        int[] distances = new int[vCount];

        for (int i = 0; i < vCount; i++) {
            colors[i] = 0;
            parents[i] = -1;
            distances[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(rootIndex);
        colors[rootIndex] = 1;
        distances[rootIndex] = 0;
        onVertex.accept(root, 0);

        while (queue.size() > 0) {
            int uIndex = queue.poll();
            int k = distances[uIndex];
            for (int vIndex : adjacencyLists.get(uIndex)) {
                if (colors[vIndex] == 0) {
                    queue.add(vIndex);
                    colors[vIndex] = 1;
                    distances[vIndex] = k + 1;
                    parents[vIndex] = uIndex;
                    onVertex.accept(indexToVertex.get(vIndex), k + 1);
                }
            }
            colors[uIndex] = 2;
        }

        return new GraphTraverseInfo(colors, parents, distances);
    }

    public static class GraphTraverseInfo {
        private int[] colors; // 0: white; 1: grey; 2: black
        private int[] parents;
        private int[] distances;

        public GraphTraverseInfo(int[] colors, int[] parents, int[] distances) {
            this.colors = colors;
            this.parents = parents;
            this.distances = distances;
        }

        public int[] getColors() {
            return colors;
        }

        public int[] getParents() {
            return parents;
        }

        public int[] getDistances() {
            return distances;
        }
    }

    @Override
    public String toString() {
        return "NonWeightedAdjacencyListGraph{" +
                "adjacencyLists=" + adjacencyLists +
                '}';
    }
}
