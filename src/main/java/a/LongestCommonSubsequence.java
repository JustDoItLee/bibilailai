package a;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 */
public class LongestCommonSubsequence {
    String str1;
    String str2;

    public int longestCommonSubsequence(String text1, String text2) {
        str1 = text1;
        str2 = text2;
        return dp(text1.length() - 1, text2.length() - 1);
    }

    int dp(int i, int j) {
        // 空串的 base case
        if (i == -1 || j == -1) {
            return 0;
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            // 这边找到一个 lcs 的元素，继续往前找
            return dp(i - 1, j - 1) + 1;
        } else {
            // 谁能让 lcs 最长，就听谁的
            return Math.max(dp(i - 1, j), dp(i, j - 1));
        }
    }

    int dp2(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        // 构建 DP table 和 base case
        int[][] dp = new int[m + 1][n + 1];
        //进行状态转移
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    //找到一个 lcs 中的字符
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().dp2("abcde","ace"));
    }
}
