package com.jackshenorion.dsaaj.graph.algograph;

import com.jackshenorion.dsaaj.graph.edge.DefaultEdgeByIndex;
import com.jackshenorion.dsaaj.graph.intf.IEdgeByIndex;
import com.jackshenorion.dsaaj.graph.intf.IGraph;
import com.jackshenorion.dsaaj.graph.visualize.HasGraphVisualInfo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AbstractAlgoGraph<V> implements IGraph<V> {

    protected List<V> indexToVertex;
    protected Map<V, Integer> vertexToIndex;

    public AbstractAlgoGraph() {
        indexToVertex = new ArrayList<>();
        vertexToIndex = new HashMap<>();
    }

    public int getVertexCount() {
        return indexToVertex.size();
    }

    /*BFS*/
    public GraphTraverseInfo bfs(Object root, BiConsumer onVertex) {

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
            forEachAdjacentVertex(uIndex, vIndex -> {
                if (colors[vIndex] == 0) {
                    queue.add(vIndex);
                    colors[vIndex] = 1;
                    distances[vIndex] = k + 1;
                    parents[vIndex] = uIndex;
                    onVertex.accept(indexToVertex.get(vIndex), k + 1);
                }
            });
            colors[uIndex] = 2;
        }
        return new GraphTraverseInfo(colors, parents, distances);
    }

    public HasGraphVisualInfo<V> getGraphVisualInfo() {

        return new HasGraphVisualInfo<V>() {
            @Override
            public List<V> getAllVertices() {
                return new ArrayList<>(indexToVertex);
            }

            @Override
            public Collection<IEdgeByIndex> getAllEdges() {
                List<IEdgeByIndex> edges = new ArrayList<>();
                for (int u = 0; u < getVertexCount(); u++) {
                    int finalU = u;
                    forEachAdjacentVertex(u, v -> edges.add(new DefaultEdgeByIndex(finalU, v)));
                }
                return edges;
            }

            @Override
            public int[] getVertexColors() {
                return new int[getVertexCount()];
            }

            @Override
            public Map<Integer, int[]> getEdgesByType() {
                return Collections.EMPTY_MAP;
            }
        };
    }

    abstract protected void forEachAdjacentVertex(int uIndex, Consumer<Integer> adjacentVertexConsumer);

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
}
