package a;

import java.util.HashMap;

public class FourKeysKeyboard {
    HashMap<String, Integer> map = new HashMap();

    public int maxA(int N) {
        // 可以按 N 次按键，屏幕和剪切板里都还没有 A
        return dp(N, 0, 0);
    }

    // 对于 (n, a_num, copy) 这个状态，
    // 屏幕上能最终最多能有 dp(n, a_num, copy) 个 A
    int dp(int n, int a_num, int copy) {
        // base case
        if (n <= 0) {
            return a_num;
        }
        // 避免计算重叠子问题
        if (map.containsKey(n + "*" + a_num + copy)) {
            return map.get(n + "*" + a_num + copy);
        }
        // 几种选择全试一遍，选择最大的结果
        map.put(n + "*" + a_num + copy, Math.max(
                Math.max(dp(n - 1, a_num + 1, copy),    // A
                        dp(n - 1, a_num + copy, copy)), // C-V
                dp(n - 2, a_num, a_num)        // C-A C-C
        ));
        return map.get(n + "*" + a_num + copy);
    }
    public int maxA2(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            // 按 A 键
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
                // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        // N 次按键之后最多有几个 A？
        return dp[N];
    }
}
