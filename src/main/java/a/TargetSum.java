package a;

import java.util.HashMap;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 *  
 * <p>
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class TargetSum {
    int result = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }
        backtrack(nums, 0, S);
        return result;
    }

    /**
     * 二叉树的遍历
     * <p>
     * void backtrack(int[] nums, int i, int rest) {
     * if (i == nums.length) {
     * return;
     * }
     * backtrack(nums, i + 1, rest - nums[i]);
     * backtrack(nums, i + 1, rest + nums[i]);
     * }
     */
    void backtrack(int[] nums, int i, int rest) {
        // base case
        if (i == nums.length) {
            //把target减到0
            if (rest == 0) {
                // 说明恰好凑出 target
                result++;
            }
            return;
        }
        // 给 nums[i] 选择 - 号
        rest += nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, rest);
        // 撤销选择
        rest -= nums[i];

        // 给 nums[i] 选择 + 号
        rest -= nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, rest);
        // 撤销选择
        rest += nums[i];
    }

    // 备忘录
    HashMap<String, Integer> memo = new HashMap<>();

    int dp(int[] nums, int i, int rest) {
        // base case
        if (i == nums.length) {
            if (rest == 0) {
                return 1;
            }
            return 0;
        }
        // 把它俩转成字符串才能作为哈希表的键
        String key = i + "," + rest;
        // 避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 还是穷举
        int result = dp(nums, i + 1, rest - nums[i]) + dp(nums, i + 1, rest + nums[i]);
        // 记入备忘录
        memo.put(key, result);
        return result;
    }
}
