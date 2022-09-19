package leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class Problems {
    
    public static int pivotIndex(int[] nums) {
        int leftsum = 0;
        int total = 0;
        
        for (int i : nums) {
            total = total + i;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (total - nums[i] - leftsum == leftsum) {
                return i;
            } else {
                leftsum = leftsum + nums[i];
            }
        }
        return -1;
    }
    
    public static boolean isSubSequence(String s, String t) {
        if (t.length() < s.length()) {
            return false;
        }
        
        int lastIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            boolean found = false;
            char ch1 = s.charAt(i);
            
            for (int j = lastIndex + 1; j < t.length(); j++) {
                char ch2 = t.charAt(j);
                
                if (ch1 == ch2) {
                    found = true;
                    lastIndex = j;
                    break;
                }
            }
            
            if (!found) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap();
        HashSet<Character> set = new HashSet();
        
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            
            if (!map.containsKey(ch1)) {
                if (!set.contains(ch2)) {
                    map.put(ch1, ch2);
                    set.add(ch2);
                } else {
                    map.put(ch1, '\0');
                }
            }
        }
        
        String result="";
        for (int i = 0; i < s.length(); i++) {
            result += map.get(s.charAt(i));
        }
        
        return t.equals(result);
    }
    
    private static boolean isBadVersion(int n){
        return false;
    }
    
    private static int firstBadVersion(int n, int l, int h){
        if(l > h) return -1;
        
        int mid = l+ (h-l)/2;
        
        if(mid>0 && !isBadVersion(mid-1) && isBadVersion(mid))
            return mid;
        
        if(isBadVersion(mid)) return firstBadVersion(n,l,mid);
        
        return firstBadVersion(n,mid+1,h);
        
    }
    
    public int firstBadVersion(int n) {
        return firstBadVersion(n,1,n);
    }
    
}
