package PS7;

import java.awt.geom.Point2D;
import java.util.*;


public class treehouse {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //initialize
        int n = s.nextInt(), e = s.nextInt(), p = s.nextInt();
        int[] IDs = new int[n];
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        Point2D.Double[] points = new Point2D.Double[n];
        ArrayList<Edge> cables = new ArrayList<>();

        //rank 0 for first e trees
        if (e > 0) {
            for (int i = 0; i < e; i++) {
                IDs[i] = 0;
                points[i] = new Point2D.Double(s.nextDouble(), s.nextDouble());
                putIntoGraph(i, 0, graph);
            }
        }

        //get the rest of points
        for (int i = e; i < n; i++) {
            points[i] = new Point2D.Double(s.nextDouble(), s.nextDouble());
            IDs[i] = i;
            putIntoGraph(i, i, graph);
        }

        //union all connected points
        for (int i = 0; i < p; i++) {
            int tree1 = s.nextInt(), tree2 = s.nextInt();
            union(tree1 - 1, tree2 - 1, IDs, graph);
        }

        System.out.print(String.format("%.6f", kruskal(cables, n, points, graph, IDs)));
    }


    private static double kruskal(ArrayList<Edge> cables, int n, Point2D.Double[] points, HashMap<Integer, HashSet<Integer>> graph, int[] IDs) {
        double res = 0;
        cables = getAllCableDistance(cables, n, points);
        Collections.sort(cables);

        for (Edge e : cables) {
            if (union(e.start, e.end, IDs, graph)) {
                res += e.distance;
            }
        }
        return res;
    }

    public static void putIntoGraph(int value, int key, HashMap<Integer, HashSet<Integer>> graph) {
        if (!graph.containsKey(key))
            graph.put(key, new HashSet<>());
        graph.get(key).add(value);
    }

    public static boolean union(int tree1, int tree2, int[] IDs, HashMap<Integer, HashSet<Integer>> graph) {
        //false if connected
        if (IDs[tree1] == IDs[tree2])
            return false;

        //union the small component to larger one
        if (graph.get(IDs[tree1]).size() > graph.get(IDs[tree1]).size()) {
            for (int x : graph.get(IDs[tree2])) {
                IDs[x] = IDs[tree1];
                putIntoGraph(x, IDs[tree1], graph);
            }
        } else {
            for (int x : graph.get(IDs[tree1])) {
                IDs[x] = IDs[tree2];
                putIntoGraph(x, IDs[tree2], graph);
            }
        }
        return true;
    }

    private static ArrayList<Edge> getAllCableDistance(ArrayList<Edge> cables, int n, Point2D.Double[] points) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cables.add(new Edge(i, j, points[i].distance(points[j])));
            }
        }
        return cables;
    }
}

class Edge implements Comparable<Edge> {
    int start, end;
    double distance;

    public Edge(int s, int e, double d) {
        this.start = s;
        this.end = e;
        this.distance = d;
    }

    public int compareTo(Edge o) {
        return this.distance > o.distance ? 1 : -1;
    }
}


