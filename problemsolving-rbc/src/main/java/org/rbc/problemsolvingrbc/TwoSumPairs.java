package org.rbc.problemsolvingrbc;

import java.util.*;

record Pair(int first, int second) {}

public class TwoSumPairs {


    public static List<Pair> findPairs(int[] nums, int target) {
        Set<Integer> seen  = new HashSet<>();
        Set<Pair>    pairs = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {

                int a = Math.min(num, complement);
                int b = Math.max(num, complement);
                pairs.add(new Pair(a, b));
            }
            seen.add(num);
        }
        return new ArrayList<>(pairs);
    }

    public static void main(String[] args) {
        int[] nums   = {2, 4, 3, 7, 1, 5};
        int target   = 6;
        List<Pair> result = findPairs(nums, target);
        System.out.println(result);
    }
}
