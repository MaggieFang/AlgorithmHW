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
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("input n:");
            int n = in.nextInt();
            if (n == -1) {
                break;
            }
            test.changeCoin(n);
            System.out.print("change series: ");
            for (int e : test.list) {
                System.out.print(e + "\t");
            }
            System.out.println();
        }
    }
    public void changeCoin(int n) {
        if (n == 0) {
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

