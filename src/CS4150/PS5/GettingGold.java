package PS5;
import java.util.*;

public class GettingGold {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //initialize the map and value
        int W = s.nextInt();
        int H = s.nextInt();
        int G = 0;

        char[][] map = new char[H][W];
        boolean[][] visited = new boolean[H][W];

        for (int i = 0; i < H; i++)
            map[i] = s.next().toCharArray();

        Vertex start = new Vertex(-1 , -1);

        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] == 'P')
                    start = new Vertex(i , j);

        Queue<Vertex> bag = new LinkedList<Vertex>();
        bag.add(start);
        visited[start.row][start.col] = true;

        while (!bag.isEmpty())
        {
            // take (p,v) from bag
            Vertex v = bag.remove();

            //get gold
            if (map[v.row][v.col] == 'G'){
                G++;
            }

            // not able to walk, skip
            if (map[v.row + 1][v.col] == 'T' || map[v.row - 1][v.col] == 'T' || map[v.row][v.col + 1] == 'T' || map[v.row][v.col - 1] == 'T'){
                continue;
            }

            //if v is unmarked
            //mark v
            // v.parent = p
            // for each edge vw
            // if char = '.' or 'G'
            // put (v, w) in the bag
            // in 4 different condition
            if ((map[v.row + 1][v.col] == '.' || map[v.row + 1][v.col] == 'G') && !visited[v.row + 1][v.col])
            {
                bag.add(new Vertex(v.row + 1 , v.col));
                visited[v.row + 1][v.col] = true;
            }

            if ((map[v.row - 1][v.col] == '.' || map[v.row - 1][v.col] == 'G') && !visited[v.row - 1][v.col])
            {
                bag.add(new Vertex(v.row - 1 , v.col));
                visited[v.row - 1][v.col] = true;
            }

            if ((map[v.row][v.col + 1] == '.' || map[v.row][v.col + 1] == 'G') && !visited[v.row][v.col + 1])
            {
                bag.add(new Vertex(v.row, v.col + 1));
                visited[v.row][v.col + 1] = true;
            }

            if ((map[v.row][v.col - 1] == '.' || map[v.row][v.col - 1] == 'G') && !visited[v.row][v.col - 1])
            {
                bag.add(new Vertex(v.row, v.col - 1));
                visited[v.row][v.col - 1] = true;
            }
        }
        System.out.println(G);

        s.close();
    }
}

// Idea from 2420 Node class, set a node for our vertex position
class Vertex {
    int row, col;
    public Vertex(int r , int c) {
        this.row = r;
        this.col = c;
    }

}

