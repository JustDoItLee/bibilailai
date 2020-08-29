package a;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * for 选择 in 选择列表:
 * # 做选择
 * 将该选择从选择列表移除
 * 路径.add(选择)
 * backtrack(路径, 选择列表)
 * # 撤销选择
 * 路径.remove(选择)
 * 将该选择再加入选择列表
 */
public class Backtrack {
    static List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    static List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
    static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3}).forEach(x -> {
            System.out.println(x);
        });
        System.out.println("----------");
        permute2(new int[]{1, 2, 3}).forEach(x -> {
            System.out.println(x);
        });
    }

    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>>  result = new ArrayList<>();

        getPermutation(nums, 0, result);

        return result;
    }

    public static void getPermutation(int[] nums, int k, List<List<Integer>> result) {
        if (k == nums.length - 1) {
            List<Integer> currentlist = new ArrayList<>();
            for (int n : nums) {
                currentlist.add(n);
            }
            result.add(currentlist);
        }

        for (int i = k; i < nums.length; i++) {
            swap(nums, i, k);
            getPermutation(nums, k + 1, result);
            swap(nums, i, k);
        }
    }

    public static void swap(int[] nums, int i, int j) {

        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
