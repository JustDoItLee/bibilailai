package a;

import java.util.HashMap;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        return dp(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    //"dinitrophenylhydrazine"
    //"benzalphenylhydrazone"
    //Time Limit Exceeded
    //返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
    int dp(String s1, String s2, int i, int j) {
        // base case
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (s1.charAt(i) == s2.charAt(j)) {
            // 本来就相等，不需要任何操作
            // s1[0..i] 和 s2[0..j] 的最小编辑距离等于
            // s1[0..i-1] 和 s2[0..j-1] 的最小编辑距离
            // 也就是说 dp(i, j) 等于 dp(i-1, j-1)
            return dp(s1, s2, i - 1, j - 1);  // 啥都不做
        } else {
            // 插入
            // 直接在 s1[i] 插入一个和 s2[j] 一样的字符
            // 那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
            // 操作数加一

            // 删除
            // 直接把 s[i] 这个字符删掉
            // 前移 i，继续跟 j 对比
            // 操作数加一

            // 替换
            // 直接把 s1[i] 替换成 s2[j]，这样它俩就匹配了
            // 同时前移 i，j 继续对比
            // 操作数加一
            return Math.min(
                    Math.min(dp(s1, s2, i, j - 1) + 1,    // 插入
                            dp(s1, s2, i - 1, j) + 1),    // 删除
                    dp(s1, s2, i - 1, j - 1) + 1 // 替换
            );
        }
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance3("intention", "execution"));
    }

    HashMap<String, Integer> map = new HashMap<>();

    public int minDistance2(String word1, String word2) {
        return dp(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    int dp2(String s1, String s2, int i, int j) {
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

    //dp数组
    int minDistance3(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + 1
                    );
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    //dp数组
    int minDistance4(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];
        // base case
        int pre = 1;
        for (int j = 1; j <= n; j++)
            dp[j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[j] = pre;
                else
                    dp[j] = Math.min(
                            Math.min(dp[j] + 1,
                                    dp[j - 1] + 1),
                            pre + 1
                    );
                pre = temp;
            }
            // 储存着整个 s1 和 s2 的最小编辑距离
        }
        return dp[n];
    }
}
