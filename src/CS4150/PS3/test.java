package CS4150.PS3;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {


        String s = "aabbc";
        int[] count = new int[256];
        Arrays.fill(count, 0);



        int[][][] dp = new int[5][10+1][3];
        for (int i=0; i<5; i++)
            for (int j=0; j<=10; j++)
                Arrays.fill(dp[i][j], -1);

        System.out.println(dp[0][0]);

//        for(int i = 0;i < 256;i++) {
//            System.out.println(count[i]);
//        }


    }

}
