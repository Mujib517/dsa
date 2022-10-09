
package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Problems {
    
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 97] = 1 + map[s.charAt(i) - 97];
        }
        
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 97] = map[t.charAt(i) - 97] - 1;
        }
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> res = new ArrayList();
        
        for (int i = 0; i < strs.length; i++) {
            
            if (strs[i] == "") {
                continue;
            }
            
            List<String> list = new ArrayList();
            list.add(strs[i]);
            
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                
                if (strs[j] != "" && isAnagram(strs[i], strs[j])) {
                    list.add(strs[j]);
                    strs[j] = "";
                }
            }
            
            res.add(list);
        }
        
        return res;
        
    }
    
    public List<List<String>> groupAnagramsOptimized(String[] strs) {
        List<List<String>> res = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            
            String sorted = new String(arr);
            if (!map.containsKey(sorted)) {
                List<String> list = new ArrayList();
                list.add(str);
                map.put(sorted, list);
            } else {
                List<String> list = map.get(sorted);
                list.add(str);
            }
        }
        
        res.addAll(map.values());
        
        return res;
        
    }
    
    // 3[a]2[bc] = aaabcbc
    // 3[a2[c]] =  accaccacc
    // 2[abc]3[cd]ef = abcabccdcdcdef
    public static String decodeString(String s){
        Stack<Integer> counts = new Stack();
        Stack<String> strings = new Stack();
        int i=0;
        
        String res="";
        
        while(i<s.length()){
            char ch = s.charAt(i);
            int count=0;
            if(Character.isDigit(ch)){
                // number can be of any digits so capture all the digits
                while(Character.isDigit(s.charAt(i))){
                    count = count*10 + (s.charAt(i)-'0');
                    ++i;
                }
                counts.push(count);
                
            } else if(ch=='['){
                strings.push(res);
                res="";
                ++i;
            } else if(ch==']'){
                StringBuilder temp = new StringBuilder(strings.pop());
                int l = counts.pop();
                for (int j = 0; j < l; j++) {
                    temp.append(res);
                }
                res = temp.toString();
                ++i;
            } else{
                res += s.charAt(i);
                ++i;
            }
        }
        
        return res;
    }
    
    // abcabcbb -> abc : 3
    // bbbbb -> b: 1
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet();
        int l = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            while (set.contains(ch)) {
                set.remove(s.charAt(l));
                l++;
            }
            
            set.add(ch);
            
            max = Math.max(max, i - l + 1);
        }
        
        return max;
    }
    
    // leetcode hard problem
    public static String minWinSubStr(String s, String t){
        Map<Character,Integer> setNeed = new HashMap();
        Map<Character,Integer> setHave = new HashMap();
        
        int have = 0;
        int need = t.length();
        int len=Integer.MAX_VALUE;
        int start = 0;
        int minStart = 0;
        int minEnd=0;
        
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            setHave.put(ch,0);
            setNeed.put(ch, 1+setNeed.getOrDefault(ch,0));
            
        }
        
        int i=0;
        while(i<s.length()){
            char ch = s.charAt(i);
            if(!setNeed.containsKey(ch)) {
                ++i;
                continue;
            }
            
            setHave.put(ch, setHave.getOrDefault(ch, 0) + 1);
            
            if(setHave.get(ch)<=setNeed.get(ch)) have++;
            
            while(have>=need){
                int currentLength = (i-start+1);
                if(currentLength<len) {
                    len = currentLength;
                    minStart = start;
                    minEnd = i;
                }
                char chToRemove = s.charAt(start);
                if(setHave.containsKey(chToRemove)){
                    setHave.put(chToRemove, setHave.getOrDefault(chToRemove, 1)-1);
                    if (setHave.get(chToRemove) < setNeed.get(chToRemove))
                        have--;
                }
                ++start;
            }
            
            ++i;
        }
        
        // handle no substring cases
        return len== Integer.MAX_VALUE?"": s.substring(minStart,minEnd+1);
    }
    
    private static int getMax(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++) max = Math.max(max,arr[i]);
        return max;
    }
    
    public static int characterReplacement(String s, int k) {
        int i=0,len=0,start=0;
        int[] map = new int[26];
        
        while(i<s.length()){
            char ch = s.charAt(i);
            map[ch-65]++;
            int max = getMax(map);
            int wordLen = i - start + 1;
            
            if(wordLen-max <= k) len = Math.max(len,wordLen);
            else {
                char chToRemove = s.charAt(start);
                map[chToRemove-65]--;
                ++start;
            }
            
            ++i;
        }
        
        return len;
    }
    
    public static int countSubstrings(String s){
        int count = 0;
        int i=0;
        int n = s.length();
        
        while(i<n){
            int left =i, right = i;
            
            while(left>=0 && right<n && s.charAt(left)==s.charAt(right)){
                ++count;
                --left;
                ++right;
            }
            
            
            // make sure to count even number palindromes
            // for instance aaab : aa is also a palindrome of even size
            // above loop will not take it into consideration
            
            left = i;
            right = i+1;
            
            while(left>=0&&right<n&&s.charAt(left)==s.charAt(right)){
                ++count;
                --left;
                ++right;
            }
            
            ++i;
        }
        
        return count;
        
    }
    
    public static String longestPalindromicSubString(String s){
        int n= s.length();
        int i=0;
        int len=0;
        int start =0, end=0;
        
        while(i<n){
            int left = i, right = i;
            while(left>=0 && right<n && s.charAt(left) == s.charAt(right)){
                int currentLen = right-left +1;
                if(currentLen > len){
                    start = left;
                    end = right;
                    len = currentLen;
                }
                
                left--;
                right++;
            }
            
            left = i;
            right = i+1;
            
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int currentLen = right - left + 1;
                if (currentLen > len) {
                    start = left;
                    end = right;
                    len = currentLen;
                }
                left--;
                right++;
            }
            
            ++i;
        }
        
        return s.substring(start,end+1);
    }
    
    public static int longestPalindrome(String[] words) {
        HashMap<String, Integer> words2Count = new HashMap<>();
        
        int res = 0;
        
        for (int i = 0; i < words.length; i++) {
            
            String currWord = words[i];
            String rev = "" + currWord.charAt(1) + currWord.charAt(0);
            
            if (words2Count.containsKey(rev)) {
                res += 4;
                
                if (words2Count.get(rev) == 1) {
                    words2Count.remove(rev);
                } else {
                    words2Count.put(rev, words2Count.get(rev) - 1);
                }
                
            } else {
                words2Count.put(currWord, words2Count.getOrDefault(currWord, 0) + 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : words2Count.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (word.charAt(0) == word.charAt(1) && count == 1) {
                res += 2;
                break;
            }
        }
        
        return res;
    }
    
    // search row sorted matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int i=0,j=n-1;
        
        while(i<m && j>=0){
            int val = matrix[i][j];
            if(val==target) return true;
            if(val<target) ++i;
            else --j;
        }
        
        return false;
    }
    
    private static boolean areAnagrams(String s1, String s2, int start, int end){
        int[] map = new int[26];
        int m = s1.length();
        
        System.out.println(s1);
        System.out.println(s2.substring(start,end+1));
        
        for (int i = start; i <= end; i++) {
            int index = s2.charAt(i) - 'a';
            map[index]++;
        }
        
        for(int i=0;i<m;i++){
            int index = s1.charAt(i) - 'a';
            map[index]--;
        }
        
        for(int i=0;i<map.length;i++){
            if(map[i]!=0) return false;
        }
        
        return true;
    }
    
    // permutations in a string
    // input: ab, eidbaooo output: true
    // input: ab, eidboaoo output: false
    public static boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if(m>n) return false;
        
        int i=0;
        
        while(i<=n-m){
            if(areAnagrams(s1,s2,i,i+m-1)) return true;
            
            ++i;
        }
        
        return false;
    }
}

