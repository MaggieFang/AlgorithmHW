package com.dp;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/3/18
 * Talk is Cheap,Show me the Code.
 **/
public class LongestCommonSubSeq {
    /**
     * KeyPoint:
     * X0....Xm;
     * Y0....Yn;
     * if Xm == Yn: the LCS(X0...Xm-1,Y0...Yn-1) +1;
     * if Xm != Yn; then result will be Max{LCS(X0...Xm-1,Y0...Yn),LCS(X0...Xm,Y0...Yn-1)}
     * so we can let T[i,j] be the max length of X0...i,Y0...j;
     * Base case, i = 0 or j = 0 condition.
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubSeq(char[] s1, char[] s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        int l1 = s1.length;
        int l2 = s2.length;

        int[][] C = new int[l1][l2];

        for (int i = 0; i < s1.length; i++) {
            C[i][0] = s1[i] == s2[0] ? 1 : 0;
        }
        for (int i = 0; i < s2.length; i++) {
            C[0][i] = s1[0] == s2[i] ? 1 : 0;
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                } else {
                    C[i][j] = Math.max(C[i - 1][j], C[i][j - 1]);
                }
            }
        }
        return C[l1 - 1][l2 - 1];
    }

    public int lcs(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
    }

    public static void main(String[] args) {
        char[] A = new char[]{'D', 'C', 'A', 'B', 'C', 'A'};
        char[] B = new char[]{'D', 'A', 'D', 'C', 'A', 'C'};

        LongestCommonSubSeq test = new LongestCommonSubSeq();
        System.out.println(test.longestCommonSubSeq(A, B));
    }
}