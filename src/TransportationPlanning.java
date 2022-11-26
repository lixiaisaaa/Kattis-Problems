import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Transportation Planning, Floyd-WarShall practice, O(n^4) solution
 * <p>
 * Author: Robert Li
 */
public class TransportationPlanning {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        // Number of "intersections"
        int n = s.nextInt();

        Point2D.Double[] intersections = new Point2D.Double[n];

        for (int i = 0; i < n; i++)
            intersections[i] = new Point2D.Double(s.nextInt(), s.nextInt());

        //Num of Roads
        int m = s.nextInt();

        LinkedList<LinkedList<Integer>> adjList = new LinkedList<>();

        //Initial the adjacent list
        for (int i = 0; i < n; i++)
            adjList.add(new LinkedList<>());

        // Add both side, build the graph
        for (int i = 0; i < m; i++) {
            int u = s.nextInt(), v = s.nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        System.out.format("%.10f", findBestCommuteTime(intersections, adjList));
    }

    /**
     * Main part of this solution, rather than add a new "road" and run floyd a gain,
     * only check what happen if the new "road" is added.
     *
     * @param points
     * @param adjList
     * @return
     */
    private static double findBestCommuteTime(Point2D.Double[] points, LinkedList<LinkedList<Integer>> adjList) {
        int n = points.length;
        double[][] weights = new double[n][n];
        weights = getAllConnection(weights, points);
        double[][] dist = floydWarShall(adjList, weights);
        double bestSum = getSum(dist);
        int size = dist.length;

        for (int p1 = 0; p1 < size; p1++) {
            for (int p2 = 0; p2 < size; p2++) {
                if (!adjList.get(p1).contains(p2)) {
                    double[][] newDistance = new double[size][];
                    for (int i = 0; i < size; i++)
                        newDistance[i] = dist[i].clone();

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            newDistance[i][j] = Math.min(newDistance[i][j], newDistance[i][p1] + weights[p1][p2] + newDistance[p2][j]);
                            newDistance[i][j] = Math.min(newDistance[i][j], newDistance[i][p2] + weights[p2][p1] + newDistance[p1][j]);
                        }
                    }

                    bestSum = Math.min(bestSum, getSum(newDistance));
                }
            }
        }
        return bestSum;


    }

    private static double[][] getAllConnection(double[][] weights, Point2D.Double[] points) {
        int n = points.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                weights[i][j] = points[i].distance(points[j]);

        return weights;
    }

    private static double[][] floydWarShall(LinkedList<LinkedList<Integer>> G, double[][] weights) {
        int V = G.size();
        double[][] dist = new double[V][V];
        int i, j, k;

        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (G.get(i).contains(j) || i == j) {
                    dist[i][j] = weights[i][j];
                } else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        /*for(int w = 0; w < V; w++)
            for(int x = 0; x < V; x++)
                System.out.println(dist[w][x]);*/
        return dist;
    }

    private static double getSum(double[][] dist) {
        double sum = 0.0;
        for (int w = 0; w < dist.length; w++)
            for (int x = w; x < dist.length; x++)
                sum += dist[w][x];
        return sum;
    }
}