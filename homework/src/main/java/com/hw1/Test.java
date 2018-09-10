package com.hw1;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 9/10/18
 * Talk is Cheap,Show me the Code.
 **/

import java.util.Stack;

public class Test {
    public static void main(String args[]) {
        int A = 4;
        for (int n = 1; n <= 10; n++) {
            System.out.print((int) Math.pow(A, n) + "\t" + compute(n) + "\t" + computeWithStack(n));
            System.out.println();
        }
    }

    public static long compute(int n) {
        long A = 4;
        long result = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                result *= A;
            }
            A = A * A;
            n /= 2;
        }
        return result;
    }

    public static long computeWithStack(int n) {
        long A = 4;
        Stack<Integer> stack = new Stack<>();
        while (n > 1) {
            if (n % 2 == 0) {
                stack.push(0);
            } else {
                stack.push(1);
            }
            n /= 2;
        }
        long result = A;
        while (!stack.isEmpty()) {
            int t = stack.pop();
            if (t == 0) {
                result = result * result;
            } else {
                result = result * result * A;
            }
        }
        return result;
    }
}