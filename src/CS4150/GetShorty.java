import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class GetShorty {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // n = Number of intersections      // m = Number of corridors
        int n = s.nextInt(), m = s.nextInt();

        //n*n map for distance
        double[][] map = new double[n][n];

        //construct the graph
        for (int i = 0; i < m; i++) {
            int src = s.nextInt(), dest = s.nextInt();
            double f = s.nextDouble();
            map[src][dest] = f;
            map[dest][src] = f;
        }

        s.close();

        double[] distance = new double[n];
        Arrays.fill(distance, Integer.MIN_VALUE);
        int src = 0;

        System.out.format("%.4f", dijkstra(map, distance, src, n));
    }

    /**
     * Implemented variance Dijkstra's algorithm, calculate some in terms of
     * the "longest path" with multiply factors instead of adding up distance.
     * Starting with source node and 1 as factor
     *
     * @param map
     * @param distance
     * @param src
     * @param numOfNode
     * @return
     */
    private static double dijkstra(double[][] map, double[] distance, int src, int numOfNode) {

        PriorityQueue<Vertex> p = new PriorityQueue<>();
        p.add(new Vertex(src, 1));

        while (!p.isEmpty()) {
            Vertex curr = p.poll();

            for (int nei = 0; nei < numOfNode; nei++) {
                if (map[curr.node][nei] > 0) {
                    double f = curr.factor * map[curr.node][nei];
                    if (f > distance[nei]) {
                        distance[nei] = f;
                        p.offer(new Vertex(nei, f));
                    }
                }
            }
        }
        return distance[distance.length - 1];
    }
}

/**
 * Class only for comparable and storing vertex value.
 */
class Vertex implements Comparable<Vertex> {
    int node;
    double factor;

    public Vertex(int _node, double _factor) {
        this.node = _node;
        this.factor = _factor;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.factor < o.factor ? 1 : -1;
    }
}
