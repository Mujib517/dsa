
package sorting;

import java.util.List;


public class Problems {
    
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
     public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped=true;
                }
            }
            if(!swapped) break;
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;

            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[minIndex]) minIndex = j;
            }

            swap(arr, i, minIndex);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>temp){
                arr[j+1] = arr[j];
                j--;    
            }
            arr[j+1] = temp;
        }
    }

    private static void mergeSort(int[] arr, int start, int end){
        if(start<end){
            int mid = (start+end)/2;

            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,start,mid,end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int[] result = new int[arr.length];
        int i=start,j=mid+1,k=start;

        while(i<=mid && j<=end){
            if(arr[i]<arr[j]) result[k++] = arr[i++];
            else result[k++] = arr[j++];
        }

        while(i<=mid) result[k++] = arr[i++];
        while(j<=end) result[k++] = arr[j++];

        for(int m=start;m<=end;m++){
            arr[m] = result[m];
        }
    }

    public static void mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
    }

    /* quick sort */
    private static int getPartitionIndex(int[] arr,int low, int high){
        int pivot = arr[low];
        int i = low, j = high;
        
        
        for(int val: arr) System.out.print(val+" ");
        
        while(i<j){
            while(i<=high && arr[i] <= pivot) ++i;
            while(arr[j] > pivot) --j;

            if(i<j) swap(arr,i,j);
        }

        swap(arr,j,low);

        return j;
    }
    
    private static int getPartitionIndex2(int[] arr, int low, int high){
        int pivot = arr[high];
        int i=low, j=high;
        
        while(i<j){
            
            while(j>=0 && arr[j]>=pivot) j--;
            while(arr[i]<pivot) i++;
            
            if(i<j) swap(arr,i,j);
        }
        
        swap(arr,high,i);
        return i;
    }
    
    private static int getPartitionIndex3(int[] arr, int low, int high){
        int mid = (low+high)/2;
        int pivot = arr[mid];
        int i=low,j=high;
        
        while(i<j){
            if(i<=high && arr[i]<=pivot) ++i;
            if(arr[j]>pivot) --j;
            
            if(i<j) swap(arr,i,j);
        }
        
        swap(arr,mid,j);
        return j;
    }

    private static void quickSort(int[] arr, int low, int high){
        if(low>=high) return;
        

        int pivot = getPartitionIndex3(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
}
