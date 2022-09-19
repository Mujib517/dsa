package dp;

import java.util.Arrays;
import java.util.HashMap;

public class Problems {
    
    public static int fib(int n){
        if(n==0 || n==1) return 1;
        
        return fib(n-1)+fib(n-2);
    }
    
    public static int fibOptimized(int n){
        HashMap<Integer, Integer> map = new HashMap();
        return fibOptimized(n,map);
    }
    
    public static int factorial(int n){
        if(n== 0 || n==1) return 1;
        
        return n * factorial(n-1);
    }
    
    
    /* coin change */
    
    private static int coinChangeOptimized(int[] arr,int n, int sum, int[][] cache) {
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(n<=0) return 0;
        
        if(cache[n][sum]!=-1) return cache[n][sum];
        
        return coinChangeOptimized(arr,n-1,sum,cache)
                + coinChangeOptimized(arr,n,sum-arr[n-1],cache);
    }
    
    public static int coinChangeOptimized(int[] arr, int amount){
        int[][] cache = new int[arr.length+1][amount+1];
        for(int[] row : cache){
            Arrays.fill(row,-1);
        }
        return coinChangeOptimized(arr,arr.length,amount,cache);
    }
    
    public static int coinChange(int[] arr, int sum){
        return coinChange(arr,arr.length,sum);
    }
    
    
    /* knapsack */
    
    private static int knapsack01(int[] v, int[] w,int n, int wt){
        
        if(n<=0 || wt<=0) return 0;
        
        int included = v[n-1]+ knapsack01(v,w,n-1,wt-w[n-1]);
        int excluded = knapsack01(v,w,n-1,wt);
        
        return Math.max(included,excluded);
    }
    
    public static int knapsack01(int[] vals, int[] weights, int w){
        return knapsack01(vals,weights,vals.length, w);
    }
    
    private static int knapsack01Optimized(int[] v, int[] w, int n, int wt,int[][] cache) {
        
        if (n <= 0 || wt <= 0) {
            return 0;
        }
        
        if (cache[n][wt] != -1) {
            System.out.println("reading from cache");
            return cache[n][wt];
        }
        
        int included = v[n - 1] + knapsack01Optimized(v, w, n - 1, wt - w[n - 1],cache);
        int excluded = knapsack01Optimized(v, w, n - 1, wt,cache);
        
        for(int[] row:cache){
            for(int val:row) {
                if(val!=-1)
                    System.out.print(val+" ");
            }
            System.out.println();
        }
        
        return cache[n][wt]= Math.max(included, excluded);
    }
    
    public static int knapsack01Optimized(int[] vals, int[] weights, int w) {
        int n= vals.length;
        int[][] cache = new int[n+1][w+1];
        
        for(int[] row:cache) Arrays.fill(row,-1);
        
        return knapsack01Optimized(vals, weights, n, w,cache);
    }
    
    
    private static int knapsack(int[] v, int[] w,int n, int wt) {
        if(n<0) return 0;
        if(wt<=0) return 0;
        
        if(w[n]>wt) return knapsack(v,w,n-1,wt);
        
        int included = v[n]+ knapsack(v,w,n,wt-w[n]);
        int excluded = knapsack(v,w,n-1,wt);
        
        return Math.max(included,excluded);
    }
    
    // repitition allowed
    public static int knapsack(int[] v, int[] w, int wt){
        return knapsack(v,w,v.length-1,wt);
    }
    
    /* rod cutting incomplete*/
    private static int rodCutting(int[] v, int n, int length){
        if(n<0) return 0;
        if(length<=0) return 0;
        if(n==0) return v[0]*length;
        
        int included = v[n-1]+ rodCutting(v,n-1,length-v[n-1]);
        int excluded = rodCutting(v,n-1,length);
        
        return Math.max(included,excluded);
    }
    
    public static int rodCutting(int[] v, int length) {
        return rodCutting(v,v.length,length);
    }
    
    public static int rodCuttingOptimized(int[] v, int length){
        int[] cache = new int[length+1];
        
        for(int i=1;i<=cache.length;i++){
            for(int j=0;j<i;j++){
                cache[j] = Math.max(v[j],v[i-j-1]);
            }
        }
        
        return cache[length];
    }
    
    
    /* Longest common subsequence */
    
    public static String getLCS(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        
        int[][] cache= new int[m+1][n+1];
        
        for (int i = 1; i < cache.length; i++) {
            for (int j = 1; j < cache[i].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cache[i][j] = 1+cache[i - 1][j - 1];
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }
        
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(cache[i][j]+" ");
//            }
//            System.out.println();
//        }
        
        String result ="";
        
        int i=m,j=n;
        while(i>0 && j>0){
            if(cache[i][j] == 1+cache[i-1][j-1]) {
                result = s2.charAt(j-1) + result;
                i--;
                j--;
            } else {
                j--;
            }
        }
        return result;
    }
    
    
    public static int lcs(String s1, String s2) {
        return lcs(s1,s2,s1.length(),s2.length());
    }
    
    public static int lcsMemo(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] cache = new int[m + 1][n + 1];
        
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        
        return lcsMemo(s1, s2, m, n, cache);
    }
    
    public static int lcsTabular(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        
        int[][] cache = new int[m+1][n+1];
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)) cache[i][j] = 1+cache[i-1][j-1];
                else cache[i][j] = Math.max(cache[i-1][j],cache[i][j-1]);
            }
        }
        
        return cache[m][n];
    }
    
    private static int lcs(String s1, String s2, int m, int n){
        if(m==0 || n ==0) return 0;
        
        if(s1.charAt(m-1) == s2.charAt(n-1)) return 1+ lcs(s1,s2,m-1,n-1);
        
        return Math.max(lcs(s1,s2,m-1,n),lcs(s1,s2,m,n-1));
    }
    
    private static int lcsMemo(String s1, String s2, int m, int n, int[][] cache) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        if (cache[m][n] != -1) {
            return cache[m][n];
        }
        
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return cache[m][n] = 1 + lcsMemo(s1, s2, m - 1, n - 1, cache);
        }
        
        return cache[m][n] = Math.max(lcsMemo(s1, s2, m - 1, n, cache), lcsMemo(s1, s2, m, n - 1, cache));
    }
    
    
    /* Longest palindromic subsequence */
    public static int lps(String str){
        StringBuilder sb = new StringBuilder(str);
        String s2 = sb.reverse().toString();
        
        return lps(str,s2,str.length(),s2.length());
    }
    
    private static int lps(String s1,String s2, int m, int n){
        if(m ==0 || n == 0) return 0;
        
        if(s1.charAt(m-1) == s2.charAt(n-1)) return 1+ lps(s1,s2,m-1,n-1);
        
        return Math.max(lps(s1,s2,m-1,n),lps(s1,s2,m,n-1));
    }
    
    /* Longest increasing subsequence */
    
    public static int lic(int[] arr, int n){
        int[] cache = new int[n + 1];
        Arrays.fill(cache, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    cache[i] = Math.max(cache[i], 1 + cache[j]);
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, cache[i]);
        }
        
        return max;
    }
    
    public static int lic(int[] arr){
        return lic(arr,arr.length-1);
    }
    
    
    /* Longest sum increasing  subsequence    */
    public static int lis(int[] arr){
        int n = arr.length;
        int[] cache = new int[n];
        
        for(int i=0;i<n;i++) cache[i]=arr[i];
        
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]) cache[i] = Math.max(cache[i],cache[j]+cache[i]);
            }
        }
        
// return max
int max = Integer.MIN_VALUE;
for(int i=0;i<n;i++) max = Math.max(max,cache[i]);

return max;
    }
    
    
    /* binomial coefficient
    
    NcR = n!/(n-r)!r!
    
    C(N,K) = C(N-1,k-1) + C(N-1,k)
    
    C(N,0) = C(N,N) = 1
    */
    
    public static int binCoeff(int n, int k){
        if(n==k || k==0) return 1;
        
        int f1= binCoeff(n-1,k-1);
        int f2=binCoeff(n-1,k);
        
        return f1+f2;
    }
    
    private static int binCoeffOptimized(int n, int k, int[][] cache) {
        if(k==0 || n==k) return 1;
        
        if(cache[n][k]!=0)
            return cache[n][k];
        
        
        return cache[n][k]= binCoeffOptimized(n-1,k-1,cache)
                + binCoeffOptimized(n - 1, k, cache);
    }
    
    public static int binCoeffOptimized(int n, int k){
        int[][] cache = new int[n+1][k+1];
        return binCoeffOptimized(n,k,cache);
    }
    
    
    /*
    Permuatation coefficient
    P(N,K) = P(N-1,K) + K* P(N-1,K);
    
    P(N,0) = 1
    P(N,1) = N
    */
    
    /* private methods */
    public static int perCoeff(int n, int k){
        if(k==0) return 1;
        if(k==1) return n;
        if(n==0) return 0;
        
        return perCoeff(n-1,k) + k * perCoeff(n-1,k-1);
    }
    
    
    /* tile problems
    
    Given a “2 x n” board and tiles of size “2 x 1”, count the number of ways to tile the given board using the 2 x 1 tiles
    
    1) If we place first tile vertically, the problem reduces to “count(n-1)”
    2) If we place first tile horizontally, we have to place second tile also horizontally. So the problem reduces to “count(n-2)”
    
    */
    
    public static int countTileWays(int n){
        if(n == 1 || n==2) return n;
        
        return countTileWays(n-1) + countTileWays(n-2);
    }
    
    
    /* Edit distance */
    public static int editDistance(String s1, String s2){
        return editDistance(s1,s2,s1.length(),s2.length());
    }
    
    private static int editDistance(String s1, String s2, int m, int n){
        if(m==0) return n-1; // length of s2
        if(n==0) return m-1; // lenth of s1
        
        if(s1.charAt(m-1) == s2.charAt(n-1))
            return editDistance(s1,s2,m-1,n-1);
        
        int ins = editDistance(s1,s2,m,n-1);
        int del = editDistance(s1,s2,m-1,n);
        int rep = editDistance(s1,s2,m-1,n-1);
        
        return 1+ Math.min(Math.min(ins,del),rep);
    }
    
    public static int editDistanceOptimized(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] matched= new int[n1 + 2][n2 + 2];
        
        for (int i = 0; i <= n1;i++) {
            matched[i][0] = i;
        }
        
        for (int i = 0;i <= n2;i++) {
            matched[0][i] = i;
        }
        
        for (int i = 1 ; i <= n1;i++) {
            for (int j = 1 ; j <= n2;j++) {
                if (s1.charAt(i - 1)== s2.charAt(j - 1)) {
                    matched[i][j] = matched[i - 1][j - 1];
                } else {
                    matched[i][j] = 1 + Math.min(matched[i - 1][j], Math.min(matched[i][j - 1], matched[i - 1][j - 1]));
                }
            }
        }
        return matched[n1][n2];
    }
    
    
    private static int coinChange(int[] arr, int n, int sum){
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(n<=0) return 0;
        
        return coinChange(arr,n-1,sum) + coinChange(arr,n,sum-arr[n-1]);
    }
    
    private static int fibOptimized(int n, HashMap<Integer, Integer> map){
        if(n==0 || n==1) return 1;
        
        if(map.containsKey(n)) return map.get(n);
        
        return fibOptimized(n-1) + fibOptimized(n-2);
    }
}

