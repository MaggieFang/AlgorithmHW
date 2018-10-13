package com.hw.simulation;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Task3 {
    private static final int HANDLE_CLA = 0; //处理新到
    private static final int HANDLE_CLS = 1; //处理service时间
    private static final int HANDLE_CLR = 2;//处理重发
    private static final int TYPE_SERVICE = 0;
    private static final int TYPE_BUFFER = 1;


    private static final String FILE_NAME = "output2.txt";


    public static class EventNode {
        float timeCreated;
        float timeResend;
        float timeIntoQueue;
        float timeDepature;

        public EventNode(float timeCreated) {
            this.timeCreated = timeCreated;
        }
    }

    public static class Result {
        float mean;
        float low;
        float hight;
        float serviceTime;

        public Result(float serviceTime) {
            this.serviceTime = serviceTime;
        }
    }

    public static void simulation(int type){

    }

    public static void main(String[] args) throws IOException {

        float[] SERVICETIME = new float[]{2f, 2.5f, 3f, 3.5f, 4f, 4.5f, 5f, 5.5f};
        float[] BUFFER = new float[]{3,4,5,6,7};

        int meanA = 6;

        int meanResend = 5;

//        System.out.print("service time:");
        float serviceT;


        int buffer = 5;

        float mc = 0, cla = 2, cls = Integer.MAX_VALUE, clr;
        int request = 0;

        Result[] DSet = new Result[SERVICETIME.length];
        Result[] TSet = new Result[SERVICETIME.length];

        for (int i = 0; i <SERVICETIME.length; i++) {
            LinkedList<EventNode> resendQeueu = new LinkedList<EventNode>();
            LinkedList<EventNode> bufferQueue = new LinkedList<EventNode>();

            float totalD = 0;
            float totalT = 0;

            int b = 1000;
            int count = 0;
            int m = 50;
            int times = 0;
            EventNode node;

            float[] averageT = new float[m];
            float[] averageD = new float[m];

            float totalAverageT = 0;
            float totalAverageD = 0;

            float varianceSumT = 0;
            float varianceSumD = 0;

            serviceT = SERVICETIME[i];

            while (times < m) {
                count = 0;
                totalD = 0;
                totalT = 0;

                while (count <= b) {
                    if (resendQeueu.size() == 0) {
                        clr = Integer.MAX_VALUE;
                    } else {
                        clr = resendQeueu.get(0).timeResend;
                    }
                    switch (nextOperation(cla, cls, clr)) {
                        case HANDLE_CLA:
                            node = new EventNode(cla);
                            mc = cla;
                            double r = Math.random();
                            cla = cla + (int) (-meanA * Math.log(r));  // generate the new next arrival time.

                            if (cls == Integer.MAX_VALUE) { // handler the first time.
                                cls = mc + serviceT;
                            }

                            if (request < buffer) {
                                bufferQueue.add(node);
                                node.timeIntoQueue = node.timeCreated;
                                totalD += (node.timeIntoQueue - node.timeCreated);
                                request++;
                            } else {  // if buffer full
                                node.timeResend = mc + meanResend;
                                resendQeueu.add(node);
                            }
                            break;

                        case HANDLE_CLS:
                            mc = cls;
                            cls += serviceT;
                            request--;
                            if (request <= 0) {
                                request = 0; // idle
                            }
                            if (!bufferQueue.isEmpty()) {
                                node = bufferQueue.poll();
                                node.timeDepature = mc;
                                totalT += (node.timeDepature - node.timeCreated);
                            }


                            break;

                        case HANDLE_CLR:
                            node = resendQeueu.poll();
                            mc = clr;
                            r = Math.random();
                            clr = clr + (int) (-meanResend * Math.log(r));  // generate the new next arrival time.

                            if (request >= buffer) {
                                node.timeResend = clr;
                                resendQeueu.add(node);
                            } else {
                                bufferQueue.add(node);
                                node.timeIntoQueue = mc;
                                totalD += (node.timeIntoQueue - node.timeCreated);
                                request++;
                            }

                            break;
                    }
                    count++;
                }
                averageD[times] = totalD / b;
                averageT[times] = totalT / b;

                if (times != 0) {
                    totalAverageT += averageT[times];
                    totalAverageD += averageD[times];

                    varianceSumD += averageD[times] * averageD[times];
                    varianceSumT += averageT[times] * averageT[times];


                }
//                System.out.println(totalD + "," + totalT + ",averageD=" + averageD[times] + ",averageT=" + averageT[times]);
                times++;
            }

            Result resultT = new Result(serviceT);
            resultT.mean = totalAverageT / m;
            float varianceT = varianceSumT - resultT.mean * resultT.mean;
            resultT.low = (float) (resultT.mean - 1.645 * Math.sqrt(varianceT) / Math.sqrt(m));
            resultT.hight = (float) (resultT.mean + 1.645 * Math.sqrt(varianceT) / Math.sqrt(m));
            TSet[i] = resultT;

            Result resultD = new Result(serviceT);
            resultD.mean = totalAverageD / m;
            float varianceD = varianceSumD - resultD.mean * resultD.mean;
            resultD.low = (float) (resultD.mean - 1.645 * Math.sqrt(varianceD) / Math.sqrt(m));
            resultD.hight = (float) (resultD.mean + 1.645 * Math.sqrt(varianceD) / Math.sqrt(m));
            DSet[i] = resultD;
        }
        for (int i = 0; i < DSet.length; i++) {
            Result r = DSet[i];
            System.out.println("D:" + r.mean + "," + r.low + "," + r.hight);
        }

        CategoryDataset dataset = BatchChart.createDataset(TSet);
        JFreeChart freeChart = BatchChart.createChart(dataset,"T TIME");
        BatchChart.saveAsFile(freeChart, "batchOfT.jpg", 600, 400);

        CategoryDataset dataset2 = BatchChart.createDataset(DSet);
        JFreeChart freeChart2 = BatchChart.createChart(dataset2,"D TIME");
        BatchChart.saveAsFile(freeChart2, "batchOfD.jpg", 600, 400);

    }


    public static int nextOperation(float cla, float cls, float clr) {
        int result;
        float tmp;
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
