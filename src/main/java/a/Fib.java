package a;

import java.util.Vector;

public class Fib {
    //存在大量重复计算 比如 N=20 f(18) 被计算了两次
    static int fib(int N) {
        if (N == 1 || N == 2) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    //忘录的递归解法
    static int fib2(int N) {
        if (N < 1) return 0;
        // 备忘录全初始化为 0
        int[] memo = new int[N + 1];
        // 进行带备忘录的递归
        return helper(memo, N);
    }

    static int helper(int[] memo, int n) {
        // base case
        if (n == 1 || n == 2) return 1;
        // 已经计算过
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(fib(20));
        long time2 = System.currentTimeMillis();
        System.out.println(fib2(20));
        long time3 = System.currentTimeMillis();
        System.out.println(time2 - time);
        System.out.println(time3 - time2);
    }
}
