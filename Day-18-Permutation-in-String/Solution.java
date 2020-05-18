/**
 * Day 18 - Permutation in String
 * 
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
 * In other words, one of the first string's permutations is the substring of the second string.
 * 
 * Example 1: 
 *  Input: s1 = "ab" s2 = "eidbaooo" 
 *  Output: True
 *  Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * Example 2: 
 *  Input:s1= "ab" s2 = "eidboaoo" 
 *  Output: False
 * 
 * Note: 
 *  The input strings only contain lower case letters. 
 *  The length of both given strings is in range [1, 10,000].
 * 
 * Tip: Sliding Window (The same as Day 17)
 * 
 * */

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }
    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }

    public boolean checkInclusion2(String s1, String s2) {
        int[] letters = new int[26];
        for(int i = 0; i < s1.length(); i++)
            letters[s1.charAt(i) - 'a']++;
        int start = 0, end = 0;
        while(end < s2.length()){
            if(letters[s2.charAt(end) - 'a'] > 0){
                letters[s2.charAt(end) - 'a']--;
                end++;
                if(end - start == s1.length()) return true;
            } else if(start == end){
                start++;
                end++;
            } else {
                letters[s2.charAt(start) - 'a']++;
                start++;
            }
        }
        return false;
    }

    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }
        return count == 26;
    }
}


