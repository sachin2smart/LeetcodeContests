package src.contests.leetcode339;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/

/*
    You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
      ->  The 2D array should contain only the elements of the array nums.
      ->  Each row in the 2D array contains distinct integers.
      ->  The number of rows in the 2D array should be minimal.
    Return the resulting array. If there are multiple answers, return any of them.
    Note that the 2D array can have a different number of elements on each row.

    Example 1:
    ---------
    Input: nums = [1,3,4,1,2,3,1]
    Output: [[1,3,4,2],[1,3],[1]]
    Explanation: We can create a 2D array that contains the following rows:
    - 1,3,4,2
    - 1,3
    - 1
    All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
    It can be shown that we cannot have less than 3 rows in a valid array.

    Example 2:
    ---------
    Input: nums = [1,2,3,4]
    Output: [[4,3,2,1]]
    Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
 */

public class Program2 {
    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int num : nums)
            hm.put(num, hm.getOrDefault(num, 0 ) + 1);

        List<List<Integer>> res = new ArrayList<>();

        while (hm.size() > 0) {
            List<Integer> currentList = new ArrayList<>(hm.keySet());
            res.add(currentList);
            for (int num : currentList)
                hm.computeIfPresent(num, (k, v) -> v-1 > 0 ? v-1 : null);
        }

        return res;
    }

    public static void main(String[] args) {
        Program2 program2 = new Program2();
        System.out.println(program2.findMatrix(new int[]{1,3,4,1,2,3,1}));
    }
}
