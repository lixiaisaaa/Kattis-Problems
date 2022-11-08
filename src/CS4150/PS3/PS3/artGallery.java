package PS3;

import java.util.Arrays;
import java.util.Scanner;



public class artGallery {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int k = s.nextInt();


            int[][] gallery = new int[N][2];
            for (int i=0; i<N; i++)
                for (int j=0; j<2; j++)
                    gallery[i][j] = s.nextInt();

            int[][][] dp = new int[N][k+1][3];
            for (int i=0; i<N; i++)
                for (int j=0; j<=k; j++)
                    Arrays.fill(dp[i][j], Integer.MIN_VALUE);


            //base

            dp[0][0][0] = gallery[0][0] + gallery[0][1];
            if (k > 0){
                dp[0][1][1] = gallery[0][1];
                dp[0][1][2] = gallery[0][0];}

            for (int r=1; r<N; r++) {
                for (int c=0; c<=k; c++) {

                    dp[r][c][0] = gallery[r][0] + gallery[r][1] + Math.max(dp[r-1][c][0],Math.max(dp[r-1][c][1],dp[r-1][c][2]));

                    if(c!=0){
                        dp[r][c][1] = gallery[r][1] + Math.max(dp[r-1][c-1][1], dp[r-1][c-1][0]);
                        dp[r][c][2] = gallery[r][0] + Math.max(dp[r-1][c-1][2], dp[r-1][c-1][0]);
                    }
                }
            }

            System.out.println(Math.max(dp[N-1][k][0],Math.max(dp[N-1][k][1],dp[N-1][k][2])));

            //Skip last 2 gallery
            N = s.nextInt();
            k = s.nextInt();


    }
}
