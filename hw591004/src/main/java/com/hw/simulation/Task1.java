package com.hw.simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task1 {
    private static final int HANDLE_CLA = 0;
    private static final int HANDLE_CLS = 1;
    private static final int HANDLE_CLR = 2;
    private static final String FILE_NAME = "output.txt";

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        System.out.print("interval time:");
        int meanNew = in.nextInt();

        System.out.print("orbiting time:");
        int meanResend = in.nextInt();

        System.out.print("service time:");
        int serviceT = in.nextInt();


        System.out.print("buffer size :");
        int buffer = in.nextInt();

        System.out.print("last MC :");
        int end = in.nextInt();

        FileWriter writer = new FileWriter(FILE_NAME, true);
        BufferedWriter bw = new BufferedWriter((writer));
        bw.write(String.format("%-7s %-7s %-7s %-10s %s ", "MC", "CLA", "CLS", "request", "CLR"));
        bw.newLine();

        bw.write(String.format("%-7s %-7s %-7s %-10s %s ", "0", "2", "-", "0", "[]"));
        bw.newLine();

        int mc = 0, cla = 2, cls = Integer.MAX_VALUE, clr, request = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();


        while (mc <= end) {
            if (list.size() == 0) {
                clr = Integer.MAX_VALUE;
            } else {
                clr = list.get(0);
            }
            switch (nextOperation(cla, cls, clr)) {
                case HANDLE_CLA:
                    mc = cla;
                    cla += meanNew;
                    if (cls == Integer.MAX_VALUE) { // handler the first time.
                        cls = mc + serviceT;
                    }

                    if (request < buffer) {
                        request++;
                    } else {  // if buffer full
                        list.add(mc + meanResend);
                    }
                    break;

                case HANDLE_CLS:
                    mc = cls;
                    cls += serviceT;
                    request--;
                    break;

                case HANDLE_CLR:
                    mc = clr;
                    if (request >= buffer) {
                        list.set(0, clr + meanResend);
                    } else {
                        request++;
                        list.remove(0);
                    }
                    break;
            }
            if (list.size() != 0) {
                Collections.sort(list);
            }
            bw.write(String.format("%-7s %-7s %-7s %-10s %s ", mc, cla, cls, request, list));
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }

    public static int nextOperation(int cla, int cls, int clr) {
        int result;
        int tmp;
        if (cls < cla) {
            result = HANDLE_CLS;
            tmp = cls;
        } else {
            result = HANDLE_CLA;
            tmp = cla;
        }

        if (clr <= tmp) {
            result = HANDLE_CLR;
        }

        return result;
    }
}
