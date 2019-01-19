package com.hw3;

import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/23/18
 * Talk is Cheap,Show me the Code.
 **/
public class MatrixChain {
    public static int matrixChainMultiplication(int[] C) {
        int n = C.length;

        int[][] memo = new int[n + 1][n + 1];
        int[][] T = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                memo[i][j] = Integer.MAX_VALUE;

                for (int k = i; j < n && k <= j - 1; k++) {
                    int count = memo[i][k] + memo[k + 1][j]
                            + C[i - 1] * C[k] * C[j];

                    if (count < memo[i][j]) {
                        memo[i][j] = count;
                        T[i][j] = k;
                    }

                }
            }
        }
        System.out.println(memo[1][n - 1]);
        output(T, 1, n - 1);
        System.out.println();
        return memo[1][n - 1];
    }


    public static void output(int[][] T, int i, int j) {
        if (i == j) {
            System.out.print(i);
            return;
        }
        System.out.print("(");
        output(T, i, T[i][j]);
        output(T, T[i][j] + 1, j);
        System.out.print(")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int i = 0;
        int[] C = new int[num + 1];
        while (i < C.length) {
            C[i++] = scanner.nextInt();
        }

        matrixChainMultiplication(C);
    }
}
