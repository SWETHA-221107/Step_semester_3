package main.java.sorting.class_problems;

import java.util.Arrays;

public class PairSumSorted {

    public static String pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        int[] nums1 = {-4, -1, 0, 3, 5, 9};
        int target1 = 4;

        System.out.println(pairSumSorted(nums1, target1));

        int[] nums2 = {1, 2, 3};
        int target2 = 100;

        System.out.println(pairSumSorted(nums2, target2));
    }
}