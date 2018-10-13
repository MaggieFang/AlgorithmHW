package com.dp;


/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/3/18
 * Talk is Cheap,Show me the Code.
 **/
public class CutRod {
    // p[0,1,2....],p[0]= 0;


    private int cutRod(int[] p) {
        int[] r = new int[p.length];
        r[0] = 0;
        r[1] = p[1];
        for (int i = 2; i < p.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                curMax = Math.max(curMax, r[j] + p[i - j]);
            }
            r[i] = curMax;
        }

        return r[p.length - 1];
    }

    /**
     * not only return the max value,but also print the cut scheme
     */
    private int cutPieces(int[] p) {
        int[] r = new int[p.length]; // r[i]: the max value earned cut i length rod
        int[] pieces = new int[p.length]; // pieces: the first piece to cut
        r[0] = 0;
        r[1] = p[1];
        for (int i = 0; i < p.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (curMax < p[j] + r[i - j]) {
                    curMax = p[j] + r[i - j];
                    pieces[i] = j;
                }

            }
            r[i] = curMax;
        }

        int last = p.length - 1;
        while (last > 0) {
            int piece = pieces[last];
            System.out.println(piece);
            last -= piece;
        }

        return r[p.length - 1];
    }

}
