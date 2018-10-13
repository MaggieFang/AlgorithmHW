package com.dp;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 10/4/18
 * Talk is Cheap,Show me the Code.
 **/
public class LongestIncreasingSubSeq300 {
    /**
     * KEYPOINTS:
     * <p>
     * Question to clarify:
     * 1)if the result naboring is equal? we include 1 or 2? e.g.  2,6,7,2,7,10 the second 7  included?
     * <p>
     * <p>
     * </p>
     * PSEUDOCODE:
     * <pre>
     *  <code>
     *
     * </code>
     * </pre>
     * TIME COMPLEXITY:
     * <p>
     * SPACE COMPLEXITY:
     * <p>
     **/
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] c = new int[n];
        c[n - 1] = 1;
        int min = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (min > nums[i]) {
                c[i] = c[i + 1] + 1;
            } else {
                c[i] = c[i + 1];
            }
            min = Math.min(min, nums[i]);
        }
        return c[0];
    }

    public int lengthOfLISLtoR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] c = new int[n];
        c[0] = 1;
        int max = nums[0];

        for (int i = 1; i < n; i++) {

            if (max <= nums[i]) {
                c[i] = c[i - 1] + 1;
            } else {
                c[i] = c[i - 1];
            }
            max = Math.max(max, nums[i]);
        }
        return c[n - 1];

    }

    public static void main(String[] args) {


    }
}
