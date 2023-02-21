package PS4;
/*
Mr. Johnson likes to build houses. In fact, he likes it so much that he has built a lot of houses that he has not yet placed on plots. He has recently acquired 𝑁 circular plots.
The city government has decided that there can be only one house on each plot, and a house cannot touch the boundary of the plot.

Mr. Johnson has 𝑀 circular houses and 𝐾 square houses. Help him figure out how many of the plots he can fill with houses so that he can get some money back on his investments.

Input:
The first line of input consists of 3 space-separated integers 𝑁, 𝑀, and 𝐾.
The second line contains 𝑁 space-separated integers, where the 𝑖th integer denotes the radius 𝑟𝑖 of the 𝑖th plot.
The third line contains 𝑀 space-separated integers, where the 𝑖th integer denotes the radius 𝑟𝑖 of the 𝑖th circular house.
The fourth line contains 𝐾 space-separated integers, where the 𝑖th
integer denotes the side length 𝑠𝑖 of the 𝑖th square house.

Output:
Output the largest number of plots he can fill with houses. Your algorithm must finish in less than 1 second per test case.
 */

import java.util.Arrays;
import java.util.Scanner;

public class squarePegInARoundHole {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();
        int M = s.nextInt();
        int K = s.nextInt();

        int[] radiusOfPlots = new int[N];
        double[] radiusOfHouse = new double[M + K];
        int houseIndex = 0;

        for (int i = 0; i < N; i++) radiusOfPlots[i] = s.nextInt();
        for (int i = 0; i < M; i++) radiusOfHouse[houseIndex++] = s.nextInt();
        //get the radius for square
        for (int i = 0; i < K; i++) radiusOfHouse[houseIndex++] = getRadius(s.nextInt());

        //sort, compare them from small to large
        Arrays.sort(radiusOfPlots);
        Arrays.sort(radiusOfHouse);

        int res = 0;

        for (int i = 0; i < houseIndex; i++) {
            for (int j = 0; j < N; j++) {
                if (radiusOfPlots[j] > radiusOfHouse[i]) {
                    //found the smallest house we can just fit to the smallest plot for now
                    // and mark it used.
                    radiusOfPlots[j] = Integer.MIN_VALUE;
                    res++;
                    //end looking for this plot
                    break;
                }
            }
        }
        System.out.println(res);
    }

    private static double getRadius(int x) {
        return x / Math.sqrt(2);
    }


}
