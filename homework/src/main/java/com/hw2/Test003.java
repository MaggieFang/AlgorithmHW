package com.hw2;

import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/1/18
 * Talk is Cheap,Show me the Code.
 **/
public class Test003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int num = scanner.nextInt();
        int i = 0;
        int[] C = new int[num];
        while (i < num) {
            C[i++] = scanner.nextInt();
        }

        System.out.println(getChangeCoin(C, v));
    }

    public static int getChangeCoin(int[] coins, int n) {
        {

            int memo[] = new int[n + 1];

            // Base case (If given value V is 0)
            memo[0] = 0;

            // Initialize all memo values as Infinite
            for (int i = 1; i <= n; i++)
                memo[i] = Integer.MAX_VALUE;

            // Compute minimum coins required for all
            // values from 1 to V
            for (int i = 1; i <= n; i++)
            {
                // Go through all coins smaller than i
                for (int j = 0; j < coins.length; j++)
                    if (coins[j] <= i)
                    {
                        int sub_res = memo[i - coins[j]];
                        if (sub_res != Integer.MAX_VALUE
                                && sub_res + 1 < memo[i])
                            memo[i] = sub_res + 1;
                    }

            }
            return memo[n];

        }
    }



}
