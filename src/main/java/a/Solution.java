package a;

import java.util.*;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class Solution {
    HashMap<String, Integer> map = new HashMap<>();

    public int minDistance(String word1, String word2) {
        return dp(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    int dp(String s1, String s2, int i, int j) {
        if (map.containsKey(i + "*" + j)) {
            return map.get(i + "*" + j);
        }
        // base case
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (s1.charAt(i) == s2.charAt(j)) {
            map.put(i + "*" + j, dp(s1, s2, i - 1, j - 1));  // 啥都不做
        } else {
            map.put(i + "*" + j, Math.min(
                    Math.min(dp(s1, s2, i, j - 1) + 1,    // 插入
                            dp(s1, s2, i - 1, j) + 1),    // 删除
                    dp(s1, s2, i - 1, j - 1) + 1 // 替换
            ));
        }
        return map.get(i + "*" + j);
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("intention", "execution"));
    }
}
