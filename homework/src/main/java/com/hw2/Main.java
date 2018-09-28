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
        int[] memo = new int[v+1];
        System.out.println(getChangeCoin(C, v, memo));
    }

    public static int getChangeCoin(int[] C, int n, int[] memo) {
        if (C == null || C.length == 0) {
            return 0;
        }
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            memo[n] = 1;
            return 1;
        }


        for (int i = C.length -1; i >=0 ; i--) {
            if()
        }

        if (memo[n] != 0) {
            return memo[n];
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n/2; i++) {
                int tmp = getChangeCoin(C,i,memo) + getChangeCoin(C,n-i,memo);
                if (min > tmp) {
                    min = tmp;
                }
            }
           return memo[n] = min;
        }
    }
}
