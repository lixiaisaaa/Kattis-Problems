package TSP_Approach;

import java.util.Scanner;


/**
 * DP Approach for TSP question, figured 22 vertices in 1.5 second
 */
public class TSP_DP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] e = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                e[i][j] = sc.nextInt();
                if (i == j) {
                    e[i][j] = 0x3f3f3f3f;
                }
            }
        }


        int mx = 1 << (n - 1);
        //dp[i][j]代表从点j接入到状态i的最短路径值，i由2进制表示，比如001代表1点已接入，101代表1、3点已接入
        int[][] dp = new int[mx][n - 1];
        for (int i = 0; i < n - 1; ++i) {
            //初始化从状态0接入到每个点
            dp[0][i] = e[i + 1][0];
        }


        for (int i = 1; i < mx - 1; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                dp[i][j] = 0x3f3f3f3f;
                if ((i & (1 << j)) == 0) {
                    for (int k = 0; k < n - 1; ++k) {
                        if ((i & (1 << k)) != 0) {
                            //状态转移dp[i][j] = min(dp[i集合-点k][k] + e[j][k])，由于0点一定不会在状态中，因此可以简化掉最低位，使得总状态为2^(n - 1)种
                            int t = dp[i - (1 << k)][k] + e[j + 1][k + 1];
                            if (t < dp[i][j]) {
                                dp[i][j] = t;
                            }
                        }
                    }
                }
            }
        }

        //遍历0点接入到最短路的最小值是多少，即最终的最小环
        int res = 0x3f3f3f3f;
        for (int i = 0; i < n - 1; ++i) {
            int t = e[0][i + 1] + dp[mx - 1 - (1 << i)][i];
            res = Math.min(res, t);
        }
        System.out.println(res);
    }
}