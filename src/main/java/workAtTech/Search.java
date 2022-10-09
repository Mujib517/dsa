
package workAtTech;

public class Search {
    
    private static int floor(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] == target) {
                if(mid==0 || nums[mid - 1] != target) return mid;
                high = mid-1;
            }
            
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
    
    public static int ceil(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(nums[mid]==target){
                if(mid == nums.length-1 || nums[mid+1]!=target)
                    return mid;
                else low = mid+1;
            }
            if(nums[mid]>target) high=mid-1;
            else low = mid+1;
        }
        
        return -1;
    }
    
    public static int getNegativeNumbersCount(int[] arr) {
        int low = 0, high=arr.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(arr[mid]>=0){
                if(mid==0) return 0;
                if(arr[mid-1]<0) return mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        
        return 0;
    }
    
    public static int getNextGreaterElement(int[] arr, int key) {
        int low = 0, high = arr.length-1;
        if(arr[0]>key) return arr[0];
        if(arr[high] <= key) return key;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                if(arr[mid+1]!=key) return arr[mid+1];
                low = mid+1;
            }
            if(arr[mid]<key){
                low = mid+1;
            }else high = mid-1;
        }
        return -1;
    }
    
    public static int[] searchRange(int[] arr, int key) {
        int fl = floor(arr,key);
        int cl = ceil(arr,key);
        
        return new int[]{fl, cl};
    }
    
    public static boolean isPerfectSquare(int num) {
        if(num==0 || num==1) return true;
        
        int low = 0, high = num/2;
        
        
        while(low<=high){
            int mid = (low+high)/2;
            int val = mid*mid;
            if(val==num) return true;
            if(val<num) low = mid+1;
            else high = mid-1;
        }
        return false;
    }
    
    private static boolean binSearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return false;
    }
    
    private static int binSearchMatrix(int[][] matrix, int target){
        int low = 0,high=matrix.length-1;
        int n= matrix[0].length;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(matrix[mid][0]<=target && matrix[mid][n-1]<=target) return mid;
            if(matrix[mid][0]>target) high = mid-1;
            else low = mid+1;
        }
        
        return -1;
    }
    
    public static int searchSortedRotatedArray(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] >= nums[low]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] <= nums[high]) {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    public static boolean searchMatrix(int[][] matrix, int key) {
        int row = binSearchMatrix(matrix,key);
        if(row==-1) return false;
        
        return binSearch(matrix[row],key);
    }
    
    public static int sqrt(int num){
        if(num<2) return num;
        
        int low = 1, high = num/2;
        int result=0;
        while(low<=high){
            int mid = (low+high)/2;
            
            int val = mid* mid;
            
            if(val==num) return mid;
            
            if(val>num) high = mid-1;
            else {
                low = mid+1;
                result = mid;
            }
        }
        
        return result;
    }
}
