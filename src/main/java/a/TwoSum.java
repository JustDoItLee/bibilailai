package a;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{left, right};
            }
            if (nums[left] + nums[right] < target) {
                left++;
            }
            if (nums[left] + nums[right] > target) {
                right--;
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int v = target - nums[i];
            if (map.containsKey(v)) {
                return new int[]{map.get(v), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * nums 中可能有多对儿元素之和都等于 target，请你的算法返回所有和为 target 的元素对儿，其中不能出现重复。
     * 比如说输入为 nums = [1,3,1,2,2,3], target = 4，那么算法返回的结果就是：[[1,3],[2,2]]
     */
    public List<int[]> twoSum3(int[] nums, int target) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int lv = nums[left];
            int rv = nums[right];
            if (sum < target) {
                while (left < right && nums[left] == lv) {
                    left++;
                }
            } else if (sum > target) {
                while (left < right && nums[right] == rv) {
                    right--;
                }
            } else {
                res.add(new int[]{left, right});
                while (left < right && nums[left] == lv) {
                    left++;
                }
                while (left < right && nums[right] == rv) {
                    right--;
                }
            }
        }
        return res;
    }

    /**
     * 100sum
     * 调用前 先给数组排序  Arrays.sort(nums);
     */
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int sz = nums.length;
        //至少2sum
        if (n < 2 || sz < n) {
            return res;
        }
        //从2sum开始
        if (n == 2) {
            //双指针
            int left = start;
            int right = sz - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                int lv = nums[left];
                int rv = nums[right];
                if (sum < target) {
                    while (left < right && nums[left] == lv) {
                        left++;
                    }
                } else if (sum > target) {
                    while (left < right && nums[right] == rv) {
                        right--;
                    }
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(lv);
                    l.add(rv);
                    res.add(l);
                    while (left < right && nums[left] == lv) {
                        left++;
                    }
                    while (left < right && nums[right] == rv) {
                        right--;
                    }
                }
            }
        } else {
            // n > 2时 递归计算（n - 1）sum 的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    //（n - 1）sum + num[i] = nsum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = new TwoSum().twoSum2(new int[]{3, 2, 4}, 6);
//        Arrays.stream(arr).forEach(x -> System.out.println(x));
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
        Arrays.sort(arr);
        System.out.println(new TwoSum().nSumTarget(arr, 3, 0, 0));
    }
}
