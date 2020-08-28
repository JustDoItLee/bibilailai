package a;

import java.util.Arrays;

/**
 * 凑零钱问题
 * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
 * 最优子结构
 */
public class CoinChange {
    // coins 中是可选硬币面值，amount 是目标金额
    static int coinChangeSolution(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 1) return -1;
        int[] dp = new int[amount + 1];  //dp[i]表示达到i用的最少硬币数
        //amount+1 表示不可到达
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 8
                // 1   dp[8] dp[7] + 1 (1)
                // 2   dp[8] dp[6] + 1 (2)
                // 3   dp[8] dp[3] + 1 (5)
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(coinChangeSolution(coins, amount));
    }
}
