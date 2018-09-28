package com.hw2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 9/27/18
 * Talk is Cheap,Show me the Code.
 **/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int num = scanner.nextInt();
        int i = 0;
        int[] C = new int[num];
        while (i < num) {
            C[i++] = scanner.nextInt();
        }
        Arrays.sort(C);

        int[] memo = new int[v + 1];
        for (int j = 0; j < memo.length; j++) {
            memo[j] = -1;
        }

        System.out.println(getChangeCoin(C, v, memo));
    }

    public static int getChangeCoin(int[] C, int n, int[] memo) {
        if (memo[n] != -1) {
            return memo[n];
        }

        if (n == 0) {
            memo[0] = 0;
            return 0;
        }

        if (n == 1) {
            memo[n] = 1;
            return 1;
        }

        int result = n;

        for (int i = 0; i < C.length; i++) {
            if (n - C[i] > 0) {
                int tmp = getChangeCoin(C, n - C[i], memo) +1 ;
                if (tmp < result) {
                    result = tmp;
                }
            }
        }
        memo[n] = result;
        return memo[n];

    }

}
