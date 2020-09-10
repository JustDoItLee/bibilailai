package a;

import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return new TwoSum().nSumTarget(nums, 4, 0, target);
    }
}
