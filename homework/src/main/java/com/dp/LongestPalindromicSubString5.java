package com.dp;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/4/18
 * Talk is Cheap,Show me the Code.
 **/
public class LongestPalindromicSubString5 {
    public String longestPalindromeWrong(String s) {
        /**
         * T[i,j]: the LPS of S[i,j];
         *
         * base case:
         * T[i,i] = s[i];
         * T[i-1,i] = null if s[i-1] != s[i]; and  s[i-1,i] otherwises;
         *
         * T[i,j]:
         * if s[i] == s[j];
         *      if T[i+1,j-1] is a palindrome from i+1..j-1
         *          T[i,i] = s[i]+T[i+1,j-1]+s[j];
         *      else
         *           T[i,j] = T[i+1,j-1];
         * else
         *     T[i,j] = T[i+1,j-1];
         **/

        if (s == null || s.length() == 0) {
            return "";
        }


        if (s.length() == 1) {
            return s.substring(0, 1);
        }

        char[] chars = s.toCharArray();
        int l = s.length();
        StringBuilder[][] t = new StringBuilder[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                t[i][j] = new StringBuilder();

            }
        }

        t[0][0].append(chars[0]);

        for (int i = 1; i < l; i++) {
            t[i][i].append(chars[i]);
            if (chars[i - 1] == chars[i]) {
                t[i - 1][i].append(chars[i - 1]).append(chars[i]);
            }
        }


        for (int i = 0; i < l; i++) {
            for (int j = i+2; j < l; j++) {
                if (chars[i] == chars[j]) {
                    if (i + 1 <= j - 1) {
                        if (t[i + 1][j - 1].length() == j - i - 1) { // the t[i+1,j-1] begins i+1 and j-1;
                            t[i][j].append(chars[i]).append(t[i+1][j-1]).append(chars[j]);
                        }else {
                            t[i][j].append(t[i+1][j-1]);
                        }
                    }
                }else {
                    t[i][j].append(t[i+1][j-1]);
                }
            }
        }

        return t[0][l-1].toString();

    }

    public String longestPalindromeRight(String s) {
        /**
         * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
         *
         * T[i,j]: the LPS of S[i,j];
         *
         * base case:
         * T[i,i] = s[i];
         * T[i-1,i] = null if s[i-1] != s[i]; and  s[i-1,i] otherwises;
         *
         * T[i,j]:
         * if s[i] == s[j];
         *      if T[i+1,j-1] is a palindrome from i+1..j-1
         *          T[i,i] = s[i]+T[i+1,j-1]+s[j];
         *      else
         *           T[i,j] = T[i+1,j-1];
         * else
         *     T[i,j] = Longer{T[i+1,j],T[i,j-1]};
         **/

        if (s == null || s.length() == 0) {
            return "";
        }


        if (s.length() == 1) {
            return s.substring(0, 1);
        }

        char[] chars = s.toCharArray();
        int l = s.length();
        StringBuilder[][] t = new StringBuilder[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                t[i][j] = new StringBuilder();

            }
        }

        t[0][0].append(chars[0]);

        for (int i = 1; i < l; i++) {
            t[i][i].append(chars[i]);
            if (chars[i - 1] == chars[i]) {
                t[i - 1][i].append(chars[i - 1]).append(chars[i]);
            }else {
                t[i - 1][i].append(chars[i - 1]);
            }
        }


        for (int j = 2; j < l; j++) {
            for (int i = 0; i < j- 1; i++) {
                if (chars[i] == chars[j]) {
                    if (i + 1 <= j - 1) {
                        if (t[i + 1][j - 1].length() == j - i - 1) { // the t[i+1,j-1] begins i+1 and j-1;
                            t[i][j].append(chars[i]).append(t[i+1][j-1]).append(chars[j]);
                        }else {
                            t[i][j].append(t[i+1][j-1]);
                        }
                    }
                }else {
                    if(t[i][j-1].length() > t[i+1][j].length()){
                        t[i][j].append(t[i][j-1]);
                    }else {
                        t[i][j].append(t[i+1][j]);
                    }
                }
            }
        }

        return t[0][l-1].toString();
    }
    public static void main(String[] args){
        LongestPalindromicSubString5 test = new LongestPalindromicSubString5();
        System.out.println(test.longestPalindromeRight("abb"));
        System.out.println(test.longestPalindromeRight("caba"));
        System.out.println(test.longestPalindromeRight("babadada"));
    }
}
