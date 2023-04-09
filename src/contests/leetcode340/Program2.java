package src.contests.leetcode340;

import java.util.*;

//  https://leetcode.com/problems/sum-of-distances/
/*
    You are given a 0-indexed integer array nums.
    There exists an array arr of length nums.length,
        where arr[i] is the sum of |i - j| over all j such that
            nums[j] == nums[i] and j != i.
        If there is no such j, set arr[i] to be 0.

    Return the array arr.

    Example 1:
    ---------
    Input: nums = [1,3,1,1,2]
    Output: [5,0,3,4,0]
    Explanation:
    When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
    When i = 1, arr[1] = 0 because there is no other index with value 3.
    When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
    When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
    When i = 4, arr[4] = 0 because there is no other index with value 2.

    Example 2:
    ---------
    Input: nums = [0,5,3]
    Output: [0,0,0]
    Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 */

public class Program2 {
    // This give TLE exception
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                List<Integer> matchIdxList = new ArrayList<>();
                matchIdxList.add(i);
                for (int j = i + 1; j < n; j++) {
                    if (!visited[j] && nums[i] == nums[j]) {
                        matchIdxList.add(j);
                    }
                }
                if (matchIdxList.size() > 1) {
                    int numMatches = matchIdxList.size();

                    for (int k = 0; k < numMatches; k++) {
                        int val = 0;
                        for (int l = 0; l < numMatches; l++) {
                            if (k != l) {
                                val += Math.abs(matchIdxList.get(k) - matchIdxList.get(l));
                            }
                        }
                        arr[matchIdxList.get(k)] = val;
                        visited[matchIdxList.get(k)] = true;
                    }
                } else {
                    arr[i] = 0;
                    visited[i] = true;
                }
            }
        }
        return arr;
    }

    public long[] distance2(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i],new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        for(List<Integer> list: map.values()) {
            long lsum = 0, rsum = 0;
            long lptr = 0, rptr = list.size();

            for(int idx : list)
                rsum += idx;

            for(int idx : list){
                rptr--;
                rsum -= idx;
                arr[idx] = (idx * lptr - lsum) + (rsum - idx * rptr);
                lsum += idx;
                lptr++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Program2 program2 = new Program2();
        System.out.println(Arrays.toString(program2.distance(new int[]{1,3,1,1,2})));    //    [5,0,3,4,0]
        System.out.println(Arrays.toString(program2.distance(new int[]{0,5,3})));       //     [0,0,0]
        System.out.println(Arrays.toString(program2.distance(new int[]{1,3,1,1,3})));   //     [5,3,3,4,4]
        System.out.println("------------------------------");
        System.out.println(Arrays.toString(program2.distance2(new int[]{1,3,1,1,2})));    //    [5,0,3,4,0]
        System.out.println(Arrays.toString(program2.distance2(new int[]{0,5,3})));       //     [0,0,0]
        System.out.println(Arrays.toString(program2.distance2(new int[]{1,3,1,1,3})));   //     [5,3,3,4,4]
    }
}
