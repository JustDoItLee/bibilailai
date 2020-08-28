package a;

import java.util.Vector;

public class Fib {
    //存在大量重复计算 比如 N=20 f(18) 被计算了两次
    static long fib(long N) {
        if (N == 1 || N == 2) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    //忘录的递归解法
    static long fib2(int N) {
        if (N < 1) return 0;
        // 备忘录全初始化为 0
        long[] memo = new long[N + 1];
        // 进行带备忘录的递归
        return helper(memo, N);
    }

    static long helper(long[] memo, int n) {
        // base case
        if (n == 1 || n == 2) return 1;
        // 已经计算过
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    //dp 数组的迭代解法
    static long fib3(int N) {
        long[] dp = new long[N + 1];
        // base case
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= N; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[N];
    }

    static long fib4(int n) {
        if (n == 2 || n == 1)
            return 1;
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(fib(20));
        long time2 = System.currentTimeMillis();
        System.out.println(fib2(20));
        long time3 = System.currentTimeMillis();
        System.out.println(fib3(20));
        long time4 = System.currentTimeMillis();
        System.out.println(fib4(20));
    }
}
