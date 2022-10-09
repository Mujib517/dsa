
package workAtTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

class IntervalComparator implements Comparator<int[]>{
    
    @Override
    public int compare(int[] a, int[] b){
        return a[0]-b[0];
    }
}

public class Problems {
    
    /* arithmatic progress
    nth term = a+ (n-1) * d
    a is first term
    d is difference
    
    */
    public static boolean isArithmeticSequence(int[] arr) {
        int[] mins = get2Min(arr);
        int nthTerm = getMax(arr);
        int d = mins[1]-mins[0];
        
        int nthTermCalculated = mins[0]+ (arr.length -1 )* d;
        
        return nthTermCalculated == nthTerm;
    }
    
    public static int[][] rotateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] res = new int[n][m];
        
        int r=0,c=0;
        
        for(int j=0;j<n;j++){
            for(int i=m-1;i>=0;i--){
                System.out.print(matrix[i][j]+" ");
                res[r][c]=matrix[i][j];
                c++;
            }
            c=0;
            r++;
            System.out.println();
        }
        
        
        return res;
    }
    
    public static int[][] transposeMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] res = new int[n][m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[j][i] = matrix[i][j];
            }
        }
        
        return res;
    }
    
    public static void print2DMatrix(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    /* Kadane's algorithm */
    public static int largestContiguousSum(int[] arr) {
        int current=0;
        int max=0;
        
        for(int i=0;i<arr.length;i++){
            current = current + arr[i];
            
            if(current>max) max = current;
            if(current<0) current = 0;
        }
        
        return max;
    }
    
    /* Kadane's algorithm when all values are negative */
    public static int largestContiguousSum2(int[] arr) {
        int current = 0;
        int max = 0;
        int maxElem=Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            current = current + arr[i];
            maxElem = Math.max(maxElem,arr[i]);
            
            if (current > max) {
                max = current;
            }
            if (current < 0) {
                current = 0;
            }
        }
        
        return max == 0 ? maxElem : max;
    }
    
    private static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        
        return sum;
    }
    
    
    public static void setRowColumnZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet();
        Set<Integer> cols = new HashSet();
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        for(int row:rows){
            for(int i=0;i<n;i++) matrix[row][i] = 0;
        }
        
        for(int col:cols){
            for(int i=0;i<m;i++) matrix[i][col]=0;
        }
    }
    
    public static int[][] mergeIntervals(int[][] intervals) {
        List<int[]> list = new ArrayList();
        Arrays.sort(intervals, new IntervalComparator());
        list.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] top = list.get(list.size()-1);
            
            if (top[1] < intervals[i][0]) list.add(intervals[i]);
            else top[1] = Math.max(top[1],intervals[i][1]);
        }
        
        
        int[][] res = new int[list.size()][2];
        for(int i=0;i<list.size();i++) res[i]=list.get(i);
        
        return res;
    }
    
    
    
    /* knapsack variation */
    
    class JewelStone {
        
        int weight, value;
        
        JewelStone(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    
    private static int getMaxValue(JewelStone[] stones, int capacity, int n,int[][] dp){
        if(n<=0) return 0;
        if(capacity <= 0) return 0;
        
        int included = 0;
        
        if(dp[n][capacity]!=-1) return dp[n][capacity];
        
        if(capacity >= stones[n-1].weight)
            included = stones[n-1].value+getMaxValue(stones,capacity-stones[n-1].weight,n-1,dp);
        int excluded =getMaxValue(stones, capacity, n - 1,dp);
        
        return dp[n][capacity]= Math.max(included,excluded);
        
    }
    
    public static int getMaxValue(JewelStone[] stones, int capacity) {
        int n = stones.length;
        int[][] dp = new int[n+1][capacity+1];
        for(int[] row:dp) Arrays.fill(row, -1);
        
        return getMaxValue(stones,capacity,stones.length,dp);
    }
    
    private static boolean hasValidSubset(int[] set, int n, int sum) {
        if (sum == 0) return true;
        if (n <= 0) return false;
        if (set[n - 1] > sum) return hasValidSubset(set, n - 1, sum);
        
        return hasValidSubset(set, n - 1, sum)
                || hasValidSubset(set, n - 1, sum - set[n - 1]);
    }
    
    public static boolean hasValidSubset(int[] A, int target) {
        return hasValidSubset(A, target, A.length);
    }
    
    /* maximum sum matrix */
    
    private static int findMaxPath(int[][] M, int m, int n){
        return 0;
    }
    
    public static int findMaxPath(int[][] M) {
        int m = M.length;
        int n = M[0].length;
        return findMaxPath(M,m,n);
    }
    
    public static ArrayList<Integer>[] getPowerSet(int[] nums) {
        int n = nums.length;
        int max = 1 << n;
        
        ArrayList<Integer>[] res = new ArrayList[max];
        
        for (int i = 0; i < max; i++) {
            
            ArrayList<Integer> list = new ArrayList();
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }
            }
            
            res[i] = list;
        }
        
        return res;
    }
    
    public static int[] findRepeatAndMissingNumber(int[] arr) {
        boolean[] map = new boolean[10000];
        
        int missing = -1;
        int duplicate = -1;
        
        for (int i = 0; i < arr.length; i++) {
            if (map[arr[i]]) {
                duplicate = arr[i];
            }
            
            map[arr[i]] = true;
        }
        
        for (int i = 1; i <= arr.length; i++) {
            if (!map[i]) {
                return new int[]{duplicate, i};
            }
        }
        
        return new int[]{duplicate, missing};
    }
    
    /* Excel column number */
    public static int getColumnNumber(String s) {
        Map<Character,Integer> map = new HashMap();
        
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        map.put('E', 5);
        map.put('F', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('J', 10);
        map.put('K', 11);
        map.put('L', 12);
        map.put('M', 13);
        map.put('N', 14);
        map.put('O', 15);
        map.put('P', 16);
        map.put('Q', 17);
        map.put('R', 18);
        map.put('S', 19);
        map.put('T', 20);
        map.put('U', 21);
        map.put('V', 22);
        map.put('W', 23);
        map.put('X', 24);
        map.put('Y', 25);
        map.put('Z', 26);
        
        
        int n= s.length()-1;
        
        if(n==0) return map.get(s.charAt(n));
        
        int i=0;
        
        int res=0;
        
        while(n>=0){
            res += map.get(s.charAt(n)) * Math.pow(26,i);
            n--;
            i++;
        }
        
        return res;
    }
    
    public static String getColumnName(int n) {
        Map<Integer,Character> map = new HashMap();
        
        map.put(0,'A');
        map.put(1,'B');
        map.put(2,'C');
        map.put(3,'D');
        map.put(4,'E');
        map.put(5,'F');
        map.put(6,'G');
        map.put(7,'H');
        map.put(8,'I');
        map.put(9,'J');
        map.put(10,'K');
        map.put(11,'L');
        map.put(12,'M');
        map.put(13,'N');
        map.put(14,'O');
        map.put(15,'P');
        map.put(16,'Q');
        map.put(17,'R');
        map.put(18,'S');
        map.put(19,'T');
        map.put(20,'U');
        map.put(21,'V');
        map.put(22,'W');
        map.put(23, 'X');
        map.put(24, 'Y');
        map.put(25, 'Z');
        
        if(n<=25) return map.get(n).toString();
        
        String res="";
        
        while(n>0){
            if(map.containsKey(n)){
                res+=map.get(n);
            }else{
                int reminder = n % 26;
                res += map.get(reminder);
                n = n/26;
            }
        }
        
        return res;
    }
    
    public static List<List<Integer>> threeSum(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        
        List<List<Integer>> list =new ArrayList();
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            
            while (j < k) {
                int val = arr[i] + arr[j] + arr[k];
                if (val == 0) {
                    List<Integer> item = new ArrayList();
                    item.add(arr[i]);
                    item.add(arr[j]);
                    item.add(arr[k]);
                    list.add(item);
                    break;
                }
                if (val > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        
        return list;
    }
    
    public static boolean sumThree(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        
        for(int i=0;i<n;i++){
            int j=i+1,k=n-1;
            
            while(j<k){
                int val = arr[i]+arr[j]+arr[k];
                if(val==0) {
                    System.out.println(i+" "+j+" "+k);
                    break;
                }
                if(val>0) k--;
                else j++;
            }
        }
        
        return false;
    }
    
//    public static int largestContiguousSum(int[] arr) {
//        int totalSum = getSum(arr);
//        int maxSum = totalSum;
//
//        int p1 = 0, p2 = arr.length - 1;
//
//        while (p1 < arr.length - 1) {
//            int sum = totalSum - arr[p1];
//            maxSum = Math.max(maxSum, sum);
//            totalSum = sum;
//            p1++;
//        }
//
//        while (p2 > 0) {
//            int sum = totalSum - arr[p2];
//            maxSum = Math.max(maxSum, sum);
//            totalSum = sum;
//            p2--;
//        }
//
//        return maxSum;
//    }
//
    private static int[] get2Min(int[] arr){
        int min = arr[0];
        int min2 = Integer.MAX_VALUE;
        
        for(int i=1;i<arr.length;i++){
            if(arr[i]<min) {
                min2 = min;
                min = arr[i];
            }
        }
        
        return new int[]{min, min2};
    }
    
    private static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        
        return max;
    }
    
    public static int[] sortAlmostSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        int[] res = new int[arr.length];
        
        for(int i=0;i<=k;i++) pq.add(arr[i]);
        
        int j=0;
        res[j++] = pq.poll();
        
        for(int i=k+1;i<arr.length;i++){
            pq.add(arr[i]);
            res[j++] = pq.poll();
        }
        
        while(!pq.isEmpty()) res[j++] = pq.poll();
        
        return res;
    }
}
