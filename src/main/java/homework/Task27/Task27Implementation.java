package homework.Task27;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Task27Implementation implements Task27 {

    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    private static   class Graph extends AbstractGraph {

        private final HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();

        public Graph(int numberNodes) {
            super(numberNodes);
        }

        @Override
        public void addEdge(int first, int second) throws IndexOutOfBoundsException {
            if (Integer.max(first, second) + 1 > NUMBER_NODES) {
                throw new IndexOutOfBoundsException();
            }
            edges.merge(first, new HashSet<>(Collections.singleton(second)), (v1, v2) -> {
                v1.add(v2.iterator().next());
                return v1;
            });
            edges.merge(second, new HashSet<>(Collections.singleton(first)), (v1, v2) -> {
                v1.add(v2.iterator().next());
                return v1;
            });
        }

        @Override
        public void removeEdge(int first, int second) {
            HashSet<Integer> nodes = edges.get(first);
            if (nodes != null) {
                nodes.remove(second);
                edges.get(second).remove(first);
            }
        }

        @Override
        public boolean isEdgeExists(int first, int second) {
            HashSet<Integer> nodes = edges.get(first);
            if (nodes != null) {
                return nodes.contains(second);
            } else {
                return false;
            }
        }
    }
}
