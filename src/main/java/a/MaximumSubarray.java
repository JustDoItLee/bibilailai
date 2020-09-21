package a;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int dp_i_1 = nums[0];
        int dp_i = 0;
        int res = dp_i_1;
        for (int i = 1; i < n; i++) {
            // dp[i] = max(nums[i], nums[i] + dp[i-1])
            dp_i = Math.max(nums[i], dp_i_1 + nums[i]);
            dp_i_1 = dp_i;
            res = Math.max(res, dp_i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray2(new int[]{-3,4,-1,2}));
    }
}
