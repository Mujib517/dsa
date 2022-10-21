package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Problems {
    
    public static int buySellStocks(int[] arr){
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        
        for(int i=0;i<arr.length;i++){
            profit = Math.max(profit, arr[i] - minPrice);
            minPrice = Math.min(arr[i],minPrice);
        }
        
        return profit;
    }
    
    public static int maxWaterAreaContainer(int[] height) {
        int p1 = 0, p2 = height.length-1;
        int maxArea = Integer.MIN_VALUE;
        
        while(p1<p2){
            // area is calucluated using this formula
            // area = Min(leftBarHeight, rightBarHeight) * (distance between bars)
            int currentArea =  Math.min(height[p1],height[p2]) * (p2-p1);
            maxArea = Math.max(maxArea, currentArea);
            
            if(height[p1]<height[p2]) p1++;
            else p2--;
        }
        
        return maxArea;
    }
    
    /* total: n(n+1)/2 */
    public static void subArrays(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                printArray(arr,i,j);
            }
        }
    }
    
    
    public static int subArrayCount(int[] arr){
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                ++count;
            }
        }
        
        return count;
    }
    
    public static int subArrayCount2(int[] arr) {
        int n= arr.length;
        return (n*(n+1))/2;
    }
    
    public static void print2DMatrix(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    
    public static int[][] matrixMultiply(int[][] a, int[][] b){
        int[][] result = new int[a.length][b[0].length];
        
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b[0].length;j++){
                for(int k=0;k<b.length;k++){
                    result[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        
        return result;
    }
    
    public static boolean[] primesUptoN(int n){
        boolean[] primes = new boolean[n+1];
        Arrays.fill(primes, true);
        
        primes[0]=false;
        primes[1]=false;
        
        for(int i=2;i*i<=n;i++){
            for(int j=2*i;j<=n;j++){
                primes[j]=false;
            }
        }
        
        
        return primes;
    }
    
    /* private methods */
    private static void printArray(int[] arr, int start , int end){
        for(int i=start;i<=end;i++) System.out.print(arr[i]+" ");
        System.out.println();
    }
    
    
    public static void dutchNationalFlag(int[] arr) {
        int i=0,j=0,k=arr.length-1;
        
        while(j<=k){
            switch (arr[j]) {
                case 0 -> {
                    int temp = arr[i];
                    arr[i]= arr[j];
                    arr[j]=temp;
                    i++;
                    j++;
                }
                case 1 -> j++;
                default -> {
                    int temp2 = arr[k];
                    arr[k] = arr[j];
                    arr[j] = temp2;
                    k--;
                }
            }
        }
    }
    
    // remove duplicates from sorted arry and return count
    public static int removeDuplicates(int[] arr) {
        int count = arr.length;
        int n = arr.length;
        int i = 0;
        while (i < n - 1) {
            if (arr[i] == arr[i + 1]) {
                count--;
            }
            i++;
        }

        return count;
    }
    
    public static List<Integer> intersection(int[] a, int[] b) {
       int m = a.length,n = b.length;
       int i=0,j=0;
       
       List<Integer> res= new ArrayList();
       
       while(i<m && j<n){
           if(a[i]==b[j]){
               res.add(a[i]);
               i++;
               j++;
           }else if(a[i]>b[j]) j++;
           else i++;
       }
       
       return res;
    }
    
    
    /* a[i]-a[j] = k where i<j */
    public static int kDiffPairs(int[] a, int k) {
        int p1 = 0, p2 = 1;
        int n = a.length;

        if (n <= 1) {
            return 0;
        }

        int count = 0;

        while (p2 < n && p1 < p2) {

            while (p2 < (n - 1) && a[p2] == a[p2 + 1]) {
                p2++;
            }

            int diff = Math.abs(a[p1] - a[p2]);
            if (diff == k) {
                ++count;
                p1++;
                p2++;
            } else if (diff < k) {
                p2++;
            } else {
                p1++;
            }
        }

        return count;
    }
    
    
    private static boolean dfs(char[][] board, int i, int j, int index, String word){
        if(index == word.length()) return true;
        if(i<0 || j<0 || i>=board.length || j>=board[i].length || board[i][j]!=word.charAt(index)) return false; 
        
        char temp = board[i][j];
        board[i][j] = ' ';

        boolean found = dfs(board,i+1,j,index+1,word)
                || dfs(board, i-1,j,index+1, word)
                || dfs(board, i,j+1,index+1,word)
                || dfs(board, i,j-1, index+1, word);

        board[i][j] = temp;
        return found;
    }
    
    public static boolean wordSearchInMatrix(char[][] board, String word){
        int m = board.length;
        int n = board[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == word.charAt(0) && dfs(board,i,j,0,word)) return true;
            }
        }
        
        return false;
    }
    
    // 1<=nums[i]<=n (n is size of array)
    // constant space
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;

            if (nums[idx] < 0) {
                res.add(Math.abs(nums[i]));
            }

            nums[idx] = -nums[idx];
        }

        return res;
    }
    
    // without using division operator
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];
        
        left[0] = 1;
        right[n-1] = 1;
        
        for(int i = 1; i<n;i++){
            left[i] = nums[i-1]*left[i-1];
        }
        
        for (int i=n-2; i>=0; i--) {
            right[i] = nums[i+1] * right[i+1];
        }
        
        
        for(int i=0;i<n;i++) res[i] = left[i]*right[i];
        
        return res;
    }
    
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void backtrack(int[] arr, List<List<Integer>> res, int start){
       if(start == arr.length) res.add(toList(arr));
       else{
           for(int i=start; i<arr.length;i++){
               swap(arr,i,start);
               backtrack(arr,res,start+1);
               swap(arr,i,start);
           }
       }
    }
    
    private List<Integer> toList(int[] arr){
        List<Integer> list = new ArrayList();
        
        for(int val : arr) list.add(val);
        return list;
    }
    
    public List<List<Integer>> permutatations(int[] arr){
        List<List<Integer>> res = new ArrayList();
        backtrack(arr,res,0);
        return res;
    }
    
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int threshold = n % 2 == 0 ? n / 2 : 1 + n / 2;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> mapElement : map.entrySet()) {
            int key = mapElement.getKey();
            int value = mapElement.getValue();

            if (value >= threshold) {
                return key;
            }
        }

        return 0;
    }
    
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) >= 1) {
                return true;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return false;
    }
    
    public boolean containsNearbyDuplicateII(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }
    
    private int getValue(List<Integer> list, int index) {
        int n = list.size();
        int value = 0;
        if (index - 1 >= 0) {
            value += list.get(index - 1);
        }
        if (index < n) {
            value += list.get(index);
        }

        return value;
    }

    public List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> first = new ArrayList();
        first.add(1);
        res.add(first);

        for (int i = 1; i < numRows; i++) {
            int[] arr = new int[i + 1];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = getValue(res.get(i - 1), j);
            }

            res.add(Arrays.stream(arr).boxed().toList());
        }

        return res;
    }
    
    public List<String> summaryRanges(int[] nums) {
        int p1 = 0, p2 = 0;
        int n = nums.length;

        List<String> result = new ArrayList();

        while (p2 < n) {
            int next = p2 + 1;
            if (next < n) {
                if (nums[p2] + 1 == nums[next]) {
                    ++p2;
                } else {
                    result.add(p1 == p2 ? Integer.toString(nums[p1]) : nums[p1] + "->" + nums[p2]);
                    p1 = next;
                    p2 = next;
                }
            } else {
                result.add(p1 == p2 ? Integer.toString(nums[p1]) : nums[p1] + "->" + nums[p2]);
                p1++;
                p2++;
            }
        }

        return result;
    }
    
    public static int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            pq.add(nums[i]);
            set.add(nums[i]);
        }

        if (pq.size() < 3) {
            return pq.peek();
        }

        int max = pq.poll();
        int k = 2;

        while (k > 0 && !pq.isEmpty()) {
            max = pq.poll();
            k--;
        }

        return max;
    }
    
    public int[] plusOne(int[] digits) {
        int carry = 0;
        int n = digits.length;
        digits[n - 1] += 1;
        carry = digits[n - 1] / 10;
        digits[n - 1] = digits[n - 1] % 10;

        if (carry == 0) return digits;

        for (int i = digits.length - 2; i >= 0; i--) {
            if (carry == 0) break;
          
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }

        if (carry == 0) return digits;

        int[] res = new int[n + 1];
        res[0] = carry;
        int k = 1;

        for (int i = 0; i < n; i++) {
            res[k++] = digits[i];
        }

        return res;
    }
    
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int m = nums1.length;
        int n = nums2.length;

        int i = 0, j = 0;

        Set<Integer> res = new HashSet();

        while (i < m && j < n) {
            if (nums1[i] == nums2[j]) {
                if (!res.contains(nums1[i])) {
                    res.add(nums1[i]);
                }
                ++i;
                ++j;
            } else if (nums1[i] < nums2[j]) {
                ++i;
            } else {
                ++j;
            }
        }

        int[] result = new int[res.size()];
        int k = 0;
        for (int val : res) {
            result[k++] = val;
        }

        return result;
    }
}


