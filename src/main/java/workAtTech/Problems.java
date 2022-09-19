
package workAtTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
}
