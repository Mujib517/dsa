package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
    
    
    public static int searchInsert(int[] nums, int target) {
        return searchInsert(nums,0,nums.length-1,target);
    }
    
    private static int searchInsert(int[] nums, int low, int high, int target){
        if(low>high) return -1;
        
        int mid = low+ (high-low)/2;
        
        if(nums[mid]==target) return mid;
        if(nums[mid]<target) {
            if(mid==nums.length-1) return mid+1;
            if(mid<nums.length-1 && nums[mid+1]>target)
                return mid+1;
            return searchInsert(nums,mid+1,high,target);
        }
        if(mid>0 && nums[mid-1]<target) return mid-1;
        if(mid==0) return 0;
        return searchInsert(nums,low,mid-1,target);
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
    
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        
        while (n != 1) {
            int current = n;
            int sum = 0;
            while (current > 0) {
                int rem = current % 10;
                current = current / 10;
                sum += rem * rem;
            }
            
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            
            n = sum;
        }
        
        return true;
    }
    
    // matrix spiral order
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom= m-1;
        int left = 0, right = n-1;
        int size = m*n;
        
        while(res.size()<size){
            
            for(int i = left; i<=right;i++){
                res.add(matrix[top][i]);
            }
            top++;
            
            
            for (int i = top; i <=bottom; i++) {
                res.add(matrix[top][right]);
            }
            right--;
            
            for(int i=right;i>=left;i--){
                res.add(matrix[bottom][i]);
            }
            
            bottom--;
            
            
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            
            left++;
            
        }
        
        return res;
    }
    
    private static int getMin(int a,  int b, int c, int d){
        int min =a;
        if(b<min) min = b;
        if(c<min) min =c;
        if(d<min) min =d;
        
        return min;
    }
    
    private static int dfs(int[][] matrix, int i, int j){
        if(i<0 || j<0 || i>=matrix.length || j>=matrix[i].length || matrix[i][j]==0) return 0;
        
        int top=dfs(matrix,i+1,j);
        int bottom= dfs(matrix,i-1,j);
        int left = dfs(matrix,i,j-1);
        int right=dfs(matrix,i,j+1);
        
        return getMin(top,bottom,left,right);
    }
    
    public static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0) continue;
                matrix[i][j]= dfs(matrix,i,j);
            }
        }
        
        return matrix;
    }
    
    public static String longestCommonPrefix(String[] strs){
        String res = "";
        for (int i = 0; i < strs[0].length(); i++) {
            char ch1 = strs[0].charAt(i);
            
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    return res;
                }
                
                char ch2 = strs[j].charAt(i);
                
                if (ch1 != ch2) {
                    return res;
                }
            }
            
            res += ch1;
        }
        
        return res;
    }
    
    private static String reverseArray(int[] arr){
        String str = "";
        int n = arr.length;
        int i = n-1;
        if(arr[n-1]==0) i=n-2;
        
        for(;i>=0;i--){
            str+=arr[i];
        }
        
        return str;
    }
    
    public static String longestCommonPrefixOptimized(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        
        // initial prefix is the first element
        String prefix = strs[0];
        
        for (int j = 1; j < strs.length; j++) {
            while (strs[j].indexOf(prefix) != 0) {
                // reduce prefix size each time when it is not found in
                // the subsequent elements
                prefix = prefix.substring(0, prefix.length() - 1);
                
            }
        }
        
        return prefix;
    }
    
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        StringBuilder input1 = new StringBuilder(num1);
        StringBuilder input2 = new StringBuilder(num2);
        
        num1 = input1.reverse().toString();
        num2 = input2.reverse().toString();
        
        int m = num1.length();
        int n = num2.length();
        
        int[] res = new int[m + n];
        Arrays.fill(res, 0);
        int carry = 0;
        
        for (int i = 0; i < num2.length(); i++) {
            int item1 = num2.charAt(i) - '0';
            int j = 0;
            for (; j < num1.length(); j++) {
                int item2 = num1.charAt(j) - '0';
                int value = res[i + j] + carry + item1 * item2;
                res[i + j] = value % 10;
                // carry
                carry = value / 10;
                
            }
            res[i + j] = carry % 10;
            carry = carry / 10;
        }
        
        return reverseArray(res);
    }
    
    // approach: multi source BFS
    public static int rottenOranges(int[][] oranges){
        Queue<int[]> q = new LinkedList();
        int m = oranges.length;
        int n = oranges[0].length;
        
        int fresh = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (oranges[i][j] == 1) {
                    ++fresh;
                } else if (oranges[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        
        if (fresh == 0) {
            return 0;
        }
        
        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        
        while (!q.isEmpty() && fresh > 0) {
            // keep track of the current queue size to traverse
            int size = q.size();
            while (size-- > 0) {
                int[] cell = q.poll();
                
                for (int[] dir : directions) {
                    int i = cell[0] + dir[0];
                    int j = cell[1] + dir[1];
                    
                    if (i < 0 || j < 0 || i >= m || j >= n) {
                        continue;
                    }
                    
                    if (oranges[i][j] == 1) {
                        oranges[i][j] = 2;
                        fresh--;
                        q.add(new int[]{i, j});
                    }
                }
                
            }
            if (!q.isEmpty()) {
                ++minutes;
            }
            
        }
        
        return fresh == 0 ? minutes : -1;
    }
    
    
    public static int threeSumClosest(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int closest = Integer.MAX_VALUE;
        int minDifference = Integer.MAX_VALUE;
        
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                int difference = Math.abs(target - sum);
                if (difference == 0) {
                    return target;
                }
                if (difference < minDifference) {
                    minDifference = difference;
                    closest = sum;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return closest;
    }
    
    public static int taskSchedule(char[] tasks, int n){
        if(n==0) return tasks.length;
        
        int[] map = new int[26];
        
        for(char ch : tasks){
            map[ch-'A']+=1;
        }
        // max heap, save frequencies of each individual tasks
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        
        for(int i=0;i<map.length;i++){
            if(map[i]!=0) pq.add(map[i]);
        }
        
        // maintain list of tasks with their next schedule
        Queue<int[]> q = new LinkedList();
        int time=0;
        
        while(!pq.isEmpty() || !q.isEmpty()){
            ++time;
            if(!pq.isEmpty()){
                int task = pq.poll();
                if(task>1){
                    int[] item = new int[]{task-1, time + n};
                    q.add(item);
                }
            }
            
            // if the scheduled time has come
            if(!q.isEmpty() && q.peek()[1]==time){
                int[] temp = q.poll();
                pq.add(temp[0]);
            }
        }
        
        return time;
    }
}
