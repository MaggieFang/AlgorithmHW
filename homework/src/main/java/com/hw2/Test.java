package com.hw2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 9/27/18
 * Talk is Cheap,Show me the Code.
 **/
public class Test {
    private ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Test test = new Test();
        int[] C = {1, 5, 10, 25, 50};

        //Test P1 a)
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("input n:");
            int n = in.nextInt();
            if (n == -1) {
                break;
            }
            test.list.clear();
            test.changeCoin(n);
            System.out.print("change series:" + test.list + ",size = " + test.list.size());
            System.out.println();

            System.out.println("test p e) and verify with a), number = " + test.getChangeCoin(C, n));
        }
        //Test P1 d

    }

    public int getChangeCoin(int[] C, int n) {
        if (C == null || C.length == 0) {
            return 0;
        }
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int i = C.length - 1;
        while (n < C[i]) {
            i--;
        }
        return 1 + getChangeCoin(C, n - C[i]);
    }

    public void changeCoin(int n) {
        if (n <= 0) {
            return;
        }
        int cur;
        if (n >= 50) {
            cur = 50;
        } else if (n >= 25) {
            cur = 25;
        } else if (n >= 10) {
            cur = 10;
        } else if (n >= 5) {
            cur = 5;
        } else {
            cur = 1;
        }
        list.add(cur);
        n = n - cur;
        changeCoin(n);
    }
}

