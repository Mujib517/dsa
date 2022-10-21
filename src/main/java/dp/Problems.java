package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    public static int coinChangeBottomUp(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        
        // fill with any higher value, Interger.MAX_VALUE should also work
        Arrays.fill(dp, amount + 1);
        
        dp[0] = 0; // if amoun is zero there are 0 ways possible
        
        // calculate for each amount min number of possible ways to return change
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                int val = i - coin;
                // it is valid if val is greather or equal to zero
                if (val >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[val]);
                }
            }
        }
        
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
    
    
    /* knapsack */
    
    private static int knapsack01(int[] v, int[] w,int n, int wt){
        
        if(n<=0 || wt<=0) return 0;
        
        
        int included = 0;
        if(wt >= w[n-1])
            included =v[n-1]+ knapsack01(v,w,n-1,wt-w[n-1]);
        
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
    
    // C(i) = max(Vk + C(i-k)) 1<=k<=i
    public static int rodCuttingOptimized2(int[] v, int n) {
        int[] cache = new int[n + 1];
        
        for (int i = 1; i < cache.length; i++) {
            for (int j = 0; j < i; j++) {
                cache[i] = Math.max(cache[i],v[j]+cache[i - j-1]);
            }
        }
        
        return cache[n];
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
    
    
    /* buy sell stocks II */
    public static int buySell2(int[] prices){
        int buy = 0;
        int sell = 0;
        int profit =0;
        
        for(int i=1;i<prices.length;i++){
            
            if(prices[i]>=prices[i-1]) sell++;
            else{
                profit += prices[sell]-prices[buy];
                buy = sell =i;
            }
        }
        
        profit += prices[sell]-prices[buy];
        
        return profit;
    }
    
    /* buy sell stocks III */
    public static int buySell3(int[] prices) {
        int minPrice1 = Integer.MAX_VALUE;
        int profit1 = 0;
        int profit2 =0;
        int minPrice2 = Integer.MAX_VALUE;
        
        for (int i = 0; i < prices.length; i++) {
            minPrice1 = Math.min(minPrice1,prices[i]);
            profit1 = Math.max(profit1, prices[i]-minPrice1);
            
            minPrice2 = Math.min(minPrice2, prices[i]-profit1);
            profit2 = Math.max(profit2, prices[i]-minPrice2);
        }
        
        return profit2;
    }
    
    /* decode ways */
    
    private static int decodeWaysOptimized(char digits[], int n) {
        int count[] = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        if (digits[0] == '0') return 0;
        
        for (int i = 2; i <= n; i++) {
            count[i] = 0;
            
            if (digits[i - 1] > '0') {
                count[i] = count[i - 1];
            }
            
            if (digits[i - 2] == '1'
                    || (digits[i - 2] == '2'
                    && digits[i - 1] < '7')) {
                count[i] += count[i - 2];
            }
        }
        return count[n];
    }
    
    private static int decodeWaysMemo(char[] digits, int n, int[] dp) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (digits[0] == '0') {
            return 0;
        }
        
        if (dp[n] != -1) {
            return dp[n];
        }
        
        int count = 0;
        
        if (digits[n - 1] > '0') {
            count = decodeWaysMemo(digits, n - 1, dp);
        }
        
        if (digits[n - 2] == '1'
                || (digits[n - 2] == '2'
                && digits[n - 1] < '7')) {
            count += decodeWaysMemo(digits, n - 2, dp);
        }
        
        return dp[n] = count;
    }
    
    
    private static int decodeWays(char[] digits, int n){
        if (n == 0 || n == 1) return 1;
        if (digits[0] == '0') return 0;
        
        int count = 0;
        
        if (digits[n - 1] > '0') {
            count = decodeWays(digits, n - 1);
        }
        
        if (digits[n - 2] == '1'
                || (digits[n - 2] == '2'
                && digits[n - 1] < '7')) {
            count += decodeWays(digits, n - 2);
        }
        
        return count;
    }
    
    public static int decodeWays(String str){
        return decodeWays(str.toCharArray(),str.length());
    }
    
    /* Trapping rain water */
    public static int trappedRainWater(int[] heights) {
        int n= heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        // calculate max building heights from left to right
        int maxSofar=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            maxSofar = Math.max(maxSofar,heights[i]);
            left[i]=maxSofar;
        }
        
        // calculate max building heights from rihgt to left
        maxSofar = Integer.MIN_VALUE;
        for (int i = n-1; i >=0; i--) {
            maxSofar = Math.max(maxSofar, heights[i]);
            right[i] = maxSofar;
        }
        
        int volume=0;
        
        for(int i=0;i<n;i++){
            // pick min of left and right heights and negate building height to get volume
            volume += Math.min(left[i],right[i])-heights[i];
        }
        
        return volume;
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
    
    
    /* longest palindromic subsequence */
    private static int lps(String s, int i, int j){
        if(i>j) return 0;
        if(i==j) return 1;
        
        if(s.charAt(i) == s.charAt(j)) return 2+ lps(s,i+1,j-1);
        
        return Math.max(lps(s,i,j-1),lps(s,i+1,j));
        
    }
    
    public static int lps(String str){
        return lps(str,0,str.length()-1);
    }
    
    
    /* Find max sum path in matrix */
    public static int findMaxPath(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && j < n - 1) {
                    mat[i][j] += Math.max(mat[i - 1][j],
                            Math.max(mat[i - 1][j - 1],
                                    mat[i - 1][j + 1]));
                } else if (j > 0) {
                    mat[i][j] += Math.max(mat[i - 1][j],
                            mat[i - 1][j - 1]);
                } else if (j < n - 1) {
                    mat[i][j] += Math.max(mat[i - 1][j],
                            mat[i - 1][j + 1]);
                }
                
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(mat[m - 1][i], max);
        }
        return max;
    }
    
    
    /* word break */
    private static boolean wordBreak(String s, int i, Set<String> dict) {
        if(i==s.length()) return true;
        
        String temp="";
        for(int j=i;j<s.length();j++){
            temp += s.charAt(j);
            
            if(dict.contains(temp)){
                if(wordBreak(s,j+1,dict)) return true;
            }
        }
        
        return false;
    }
    
    public static boolean wordBreak(String s, String[] w) {
        Set<String> dict =new HashSet<>();
        dict.addAll(Arrays.asList(w));
        return wordBreak(s,0,dict);
    }
    
    private static List<String> wordBreak2(String s,int i,Set<String> dict,List<String> result) {
        if(i==s.length()) return result;
        String temp = "";
        for (int j = i; j < s.length(); j++) {
            temp += s.charAt(j);
            
            if (dict.contains(temp)) {
                result.add(temp);
                return wordBreak2(s, j + 1, dict,result);
            }
        }
        
        return result;
    }
    
    public static List<String> wordBreak2(String s, String[] w) {
        List<String> result = new ArrayList();
        Set<String> dict = new HashSet<>();
        dict.addAll(Arrays.asList(w));
        return wordBreak2(s, 0, dict,result);
    }
    
    
    private static int coinchangeMemo(int[] coins, int target, int n, int[][] dp) {
        if (target < 0) return 0;
        if (target == 0) return dp[n][target] = 1;
        if (n <= 0) return 0;
        if (dp[n][target] != -1) return dp[n][target];
        
        
        return dp[n][target]
                = coinchangeMemo(coins, target - coins[n - 1], n, dp)
                + coinchangeMemo(coins, target, n - 1, dp);
    }
    
    public static int coinChangeMemo(int[] coins, int target){
        int n = coins.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return coinchangeMemo(coins, target, n, dp);
    }
    
    
    private static int fibOptimized(int n, HashMap<Integer, Integer> map){
        if(n==0 || n==1) return 1;
        
        if(map.containsKey(n)) return map.get(n);
        
        return fibOptimized(n-1) + fibOptimized(n-2);
    }
    
    
    /* unique paths in a matrix when only right and down moves are allowed */
    public static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
    
    public static int uniquePathsTabular(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    
    /* max product */
    
    private static int max(int[] arr){
        int max = Integer.MIN_VALUE;
        
        for(int val:arr) max = Math.max(max,val);
        
        return max;
    }
    
    private static int maxProductNaive(int[] arr, int n){
        int result = arr[0];
        
        for (int i = 0; i < n; i++) {
            int mul = arr[i];
            for (int j = i + 1; j < n; j++) {
                result = Math.max(result, mul);
                mul *= arr[j];
            }
            result = Math.max(result, mul);
        }
        return result;
    }
    
    private static int maxProduct(int[] arr, int n){
        int res = Integer.MIN_VALUE;
        int min = 1, max = 1;
        
        for (int i = 0; i < n; i++) {
            int currentMax = Math.max(Math.max(arr[i], arr[i] * max), arr[i] * min);
            int currentMin = Math.min(Math.min(arr[i], arr[i] * max), arr[i] * min);
            
            // do not override max and min before, do it after calculation
            max = currentMax;
            min = currentMin;
            
            res = Math.max(max, res);
        }
        
        return res;
    }
    
    public static int maxProduct(int[] arr) {
        return maxProduct(arr,arr.length);
    }
    
    /* palindrome partitioning */
    
    private static boolean isPalindrome(String s, int i, int j){
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--)) return false;
        }
        
        return true;
    }
    
    public static int pp(String s){
        int n= s.length();
        int[][] dp = new int[n][n];
        
        for(int gap=1;gap<n;gap++){
            for(int row = 0, col=gap; row<n-gap;row++,col++){
                if(isPalindrome(s,row,col)){
                    dp[row][col] = 0;
                }else{
                    dp[row][col] = Integer.MAX_VALUE;
                    
                    for(int k=row;k<col;k++){
                        dp[row][col] = Math.min(dp[row][col],1+dp[row][k]+dp[k+1][col]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    
    // house rob
    private int rob(int[] nums, int n) {
        if (n < 0) {
            return 0;
        }
        
        int include = nums[n] + rob(nums, n - 2);
        int exclude = rob(nums, n - 1);
        
        return Math.max(include, exclude);
        
    }
    
    public int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }
    
    public int robOptimized(int[] nums) {
        int n =nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        
        for(int i=2;i<n;i++){
            dp[i] = Math.max(nums[i]+dp[i-2],dp[i-1]);
        }
        
        return dp[n-1];
    }
    
    // no extra space
    public int robOptimized2(int[] nums) {
        int n = nums.length;
        
        int rob1=0, rob2 = 0;
        
        for(int num : nums){
            int newRob = Math.max(num+rob1,rob2);
            rob1 = rob2;
            rob2 = newRob;
        }
        
        return rob2;
    }
    
    private boolean hasSum(int[] nums, int index, int target) {
        if (index >= nums.length) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        
        boolean included = false;
        if (nums[index] - target >= 0) {
            included = hasSum(nums, index + 1, nums[index] - target);
        }
        
        boolean excluded = hasSum(nums, index + 1, target);
        
        return included || excluded;
    }
    
    
    // https://leetcode.com/problems/partition-equal-subset-sum
    // TLE: 62 / 140 test cases passed.
    public boolean canPartitionNaive(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        // get the sum of all the numbers
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        
        // if sum is odd you cannot partiion it equally
        if (sum % 2 != 0) {
            return false;
        }
        
        // try to find sum/2 that would be equal partition
        // if found return true else return false
        return hasSum(nums, 0, sum / 2);
    }
    
    private static boolean hasSumNaive(int[] nums, int index, int target){
        if (target == 0) {
            return true;
        }
        if (index >= nums.length) {
            return false;
        }
        
        boolean included = false;
        if (target - nums[index] >= 0) {
            included = hasSumNaive(nums, index + 1, target - nums[index]);
        }
        
        boolean excluded = hasSumNaive(nums, index + 1, target);
        
        return included || excluded;
    }
    
    
    // https://leetcode.com/problems/partition-equal-subset-sum
    public static boolean canPartitionOptimized(int[] nums) {
        // get the sum of all the numbers
        int sum = 0;
        for(int val :  nums) sum+=val;
        int n = nums.length;
        
        // odd number, cannot partition equally
        if(sum%2!=0) return false;
        
        // create set and add all possible sum for each element
        // for instance [1,5,11,5] the last element 5 can produce either 0 (excluded) or 5 (included) sum
        Set<Integer> set = new HashSet();
        set.add(0); // initial value
        
        for(int i=n-1;i>=0;i--){
            
            // cannot modify while running a for each loop, so create a clone of the set
            Set<Integer> tempSet =  new HashSet();
            
            // add it with all the existing values of set
            for(int val:set){
                tempSet.add(val+nums[i]);
                // make sure to add value to the cloned set or create a clone from original set to avoid this line
                tempSet.add(val);
            }
            
            // assign back the cloned set to actual set
            set = tempSet;
        }
        
        return set.contains(sum/2);
    }
}
