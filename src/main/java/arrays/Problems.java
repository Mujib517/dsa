package arrays;

import java.util.Arrays;

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
}
