package a;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 */
public class SuperEggDrop {
    Map<String, Integer> map = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp2(K, N);
    }

    int dp(int K, int N) {
        int res = Integer.MAX_VALUE;
        if (K == 1) return N;
        if (N == 0) return 0;
        if (map.containsKey(K + "*" + N)) {
            return map.get(K + "*" + N);
        }
        for (int i = 1; i < N + 1; i++) {
            // 最坏情况下的最少扔鸡蛋次数
            res = Math.min(res,
                    //最坏情况下
                    Math.max(dp(K, N - i), // 没碎
                            dp(K - 1, i - 1)) //碎
                            + 1 //在第 i 楼扔了一次
            );
        }
        map.put(K + "*" + N, res);
        return res;
    }

    public int dp2(int K, int N) {
        if (!map.containsKey(K+"*"+N)) {
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K - 1, x - 1);
                    int t2 = dp(K, N - x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }

                ans = 1 + Math.min(Math.max(dp(K - 1, lo - 1), dp(K, N - lo)),
                        Math.max(dp(K - 1, hi - 1), dp(K, N - hi)));
            }

            map.put(K+"*"+N, ans);
        }

        return map.get(K+"*"+N);
    }

    public int dp3(int K, int N) {
        if (N == 1) {
            return 1;
        }
        int[][] f = new int[N + 1][K + 1];
        for (int i = 1; i <= K; ++i) {
            f[1][i] = 1;
        }
        int ans = -1;
        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= K; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][K] >= N) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SuperEggDrop().superEggDrop(3, 200));
    }
}
