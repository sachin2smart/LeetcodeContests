package src.contests.leetcode339;

//  https://leetcode.com/problems/find-the-longest-balanced-substring-of-a-binary-string/

/*
    You are given a binary string s consisting only of zeroes and ones.
    A substring of s is considered balanced if all zeroes are before ones and
        the number of zeroes is equal to the number of ones inside the substring.
    Notice that the empty substring is considered a balanced substring.

    Return the length of the longest balanced substring of s.
    A substring is a contiguous sequence of characters within a string.

    Example 1:
    ---------
    Input: s = "01000111"
    Output: 6
    Explanation: The longest balanced substring is "000111", which has length 6.

    Example 2:
    ---------
    Input: s = "00111"
    Output: 4
    Explanation: The longest balanced substring is "0011", which has length 4.

    Example 3:
    --------
    Input: s = "111"
    Output: 0
    Explanation: There is no balanced substring except the empty substring, so the answer is 0.
 */

public class Program1 {
    public int findTheLongestBalancedSubstring(String s) {
        int count  = 0;
        int n = s.length();
        int j = 0;

        for(int i=0; i<n; i++) {
            j=i;

            int count0 = 0;
            while(j<n && s.charAt(j) == '0') {
                count0++;
                j++;
            }

            int count1 = 0;
            while(j<n && s.charAt(j) == '1') {
                count1++;
                j++;
            }

            count = Math.max(count, Math.min(count0, count1));
            i = j-1;
        }
        return count*2;
    }

    public static void main(String[] args) {
        Program1 p1 = new Program1();
        System.out.println(p1.findTheLongestBalancedSubstring("010001111"));
    }
}
