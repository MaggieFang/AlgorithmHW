package com.hw2;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/1/18
 * Talk is Cheap,Show me the Code.
 **/
public class Test003 {
    public static void main(String[] args){
        int[] A = new int[]{28, 32, 45, 28, 65, 45, 29, 31, 23, 34,

                35, 31, 23, 54, 34, 25, 23, 15, 65, 38,

                64, 65, 46, 56, 36, 45, 67, 65, 54, 66,

                45, 56, 57, 45, 38, 48, 25, 26, 34, 36};
        double mean = 41.925;
       double result = 0;
        for(int i = 0;i <A.length;i++){
            result += Math.pow((A[i] - mean),2);
        }
        System.out.println(Math.sqrt(result/(A.length -1)));
        System.out.println(2.0/Math.pow(4,0.51));
    }



}
