package CS4150.PS4;

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
