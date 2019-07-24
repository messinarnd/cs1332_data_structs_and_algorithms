import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("One of the parameters passed "
                    + "in was null. Make sure both the starting vertex and "
                    + "the graph are non-null.");
        }
        if (!(graph.getAdjList().containsKey(start))) {
            throw new IllegalArgumentException("The given starting vertex does "
                    + "not exist in the graph.");
        }

        HashSet<Vertex<T>> hashes = new HashSet<>();
        List<Vertex<T>> vertices = new ArrayList<>();
        Queue<Vertex<T>> theQ = new LinkedList<>();

        theQ.add(start);
        hashes.add(start);
        vertices.add(start);

        while (!(theQ.isEmpty())) {
            Vertex<T> curr = theQ.remove();
            List<Edge<T>> adj = graph.getAdjList().get(curr);

            int ndx = 0;
            while (ndx < adj.size()) {
                Vertex<T> next = adj.get(ndx).getV();
                if (!(hashes.contains(next))) {
                    vertices.add(next);
                    hashes.add(next);
                    theQ.add(next);
                }
                ndx++;
            }
        }

        return vertices;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("One of the parameters passed "
                    + "in was null. Make sure both the starting vertex and "
                    + "the graph are non-null.");
        }
        if (!(graph.getAdjList().containsKey(start))) {
            throw new IllegalArgumentException("The given starting vertex does "
                    + "not exist in the graph.");
        }

        HashSet<Vertex<T>> hashes = new HashSet<>();
        List<Vertex<T>> vertices = new ArrayList<>();
        depthFirstHelper(graph, start, vertices, hashes);
        return vertices;
    }

    /**
     * Recursive helper method for depthFirstSearch that takes in a graph,
     * current vertex, a list of vertices, and a set of hashes of the vertices.
     * From this, it adds vertices to the list of visited vertices that will
     * be returned by the original method.
     * @param graph the graph to search through
     * @param curr the current vertex being inspected
     * @param vertices the list of vertices visited
     * @param hashes the hashes of those same vertices
     * @param <T> the generic typing of the data
     */
    private static <T> void depthFirstHelper(Graph<T> graph, Vertex<T> curr,
                                             List<Vertex<T>> vertices,
                                             HashSet<Vertex<T>> hashes) {
        hashes.add(curr);
        vertices.add(curr);
        List<Edge<T>> adj = graph.getAdjList().get(curr);

        int ndx = 0;
        while (ndx < adj.size()) {
            Vertex<T> next = adj.get(ndx).getV();
            if (!(hashes.contains(next))) {
                depthFirstHelper(graph, next, vertices, hashes);
            }
            ndx++;
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("One of the parameters passed "
                    + "in was null. Make sure both the starting vertex and "
                    + "the graph are non-null.");
        }
        if (!(graph.getAdjList().containsKey(start))) {
            throw new IllegalArgumentException("The given starting vertex does "
                    + "not exist in the graph.");
        }

        Map<Vertex<T>, Integer> path = new HashMap<>();
        PriorityQueue<Edge<T>> thePsAndQs = new PriorityQueue<>();
        Set<Vertex<T>> myVerts = graph.getAdjList().keySet();

        int i = 0;
        for (Vertex<T> vert : myVerts) {
            if (!(vert.equals((start)))) {
                path.put(vert, Integer.MAX_VALUE);
                thePsAndQs.add(new Edge<T>(start,
                        vert, Integer.MAX_VALUE));
            } else {
                path.put(vert, 0);
                thePsAndQs.add(new Edge<T>(vert, vert, 0));
                i++;
            }
        }

        Edge<T> shortest;
        int minimumDistance;
        while (!(thePsAndQs.isEmpty()) && i < myVerts.size()) {
            i++;
            shortest = thePsAndQs.remove();
            List<Edge<T>> adj = graph.getAdjList().get(shortest.getV());
            for (Edge<T> next : adj) {
                minimumDistance = path.get(shortest.getV()) + next.getWeight();
                if (path.get(next.getV()) > minimumDistance
                        && minimumDistance >= 0) {
                    path.put(next.getV(), minimumDistance);
                    thePsAndQs.remove(next);
                    thePsAndQs.add(new Edge<T>(start,
                            next.getV(), minimumDistance));
                }
            }

        }

        return path;
    }


    /**
     * Runs Kruskal's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("The graph given was null. "
                    + "Pass in a non-null graph.");
        }

        Set<Edge<T>> myTree = new HashSet<>();
        Set<Vertex<T>> myVerts = graph.getAdjList().keySet();
        Set<Edge<T>> myEdges = graph.getEdges();
        PriorityQueue<Edge<T>> thePsAndQs = new PriorityQueue<>(myEdges);
        DisjointSet<Vertex<T>> disjointSet = new DisjointSet<>(graph.
                getAdjList().keySet());

        while (myTree.size() <= (2 * myVerts.size())
                && !(thePsAndQs.isEmpty())) {
            Edge<T> currEdge = thePsAndQs.remove();
            if (!(disjointSet.find(currEdge.getU()).
                    equals(disjointSet.find(currEdge.getV())))) {
                myTree.add(new Edge<T>(currEdge.getV(),
                        currEdge.getU(), currEdge.getWeight()));
                disjointSet.union(currEdge.getU(), currEdge.getV());
                myTree.add(currEdge);
            }
        }

        if (myTree.size() != 2 * (myVerts.size() - 1)) {
            myTree = null;
        }

        return myTree;
    }
}