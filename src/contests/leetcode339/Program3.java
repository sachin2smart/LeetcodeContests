package src.contests.leetcode339;

import javafx.util.Pair;

import java.util.*;

//  https://leetcode.com/problems/mice-and-cheese/

/*
    There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.
    A point of the cheese with index i (0-indexed) is:
        reward1[i] if the first mouse eats it.
        reward2[i] if the second mouse eats it.
    You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.
    Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.

    Example 1:
    ---------
    Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
    Output: 15
    Explanation: In this example, the first mouse eats the 2nd (0-indexed) and the 3rd types of cheese, and the second mouse eats the 0th and the 1st types of cheese.
    The total points are 4 + 4 + 3 + 4 = 15.
    It can be proven that 15 is the maximum total points that the mice can achieve.

    Example 2:
    ---------
    Input: reward1 = [1,1], reward2 = [1,1], k = 2
    Output: 2
    Explanation: In this example, the first mouse eats the 0th (0-indexed) and 1st types of cheese, and the second mouse does not eat any cheese.
    The total points are 1 + 1 = 2.
    It can be proven that 2 is the maximum total points that the mice can achieve.
 */

public class Program3 {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int points = 0;

        for(int i = 0; i < n; i++) {
            reward1[i] -= reward2[i];   // create the difference sequence
            points += reward2[i];   // Assume take all from second array
        }

        Arrays.sort(reward1);

        for(int i = 0; i < k; i++) {
            points += reward1[n - 1 - i]; // pick top k starting from n-1
        }

        return points;
    }

    public int miceAndCheese2(int[] reward1, int[] reward2, int k) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        int n = reward1.length;

        for(int i=0; i<n; i++)
            pairs.add(new Pair<>(i, reward1[i]-reward2[i]));

        pairs.sort((p1, p2) -> p2.getValue().compareTo(p1.getValue()));

        int max = 0;

        for(int i=0; i<k; i++)
            max += reward1[pairs.get(i).getKey()];

        for(int i=k; i<n; i++)
            max += reward2[pairs.get(i).getKey()];

        return max;
    }

    public static void main(String[] args) {
        Program3 program3 = new Program3();
        System.out.println(program3.miceAndCheese(new int[]{1,1,3,4}, new int[]{4,4,1,1}, 2));  // 15
        System.out.println(program3.miceAndCheese2(new int[]{1,1,3,4}, new int[]{4,4,1,1}, 2));  // 15
    }
}
