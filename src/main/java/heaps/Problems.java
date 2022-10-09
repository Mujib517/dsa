package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problems {
    
    private static void swap(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static boolean hasRightChild(int index, int length){
        return 2*index+2 < length;
    }
    
    private static boolean hasLeftChild(int index, int length){
        return 2*index + 1 < length;
    }
    
    private static void heapify(int[] arr, int i){
        int leftChildIndex = 2*i+1;
        int rightChildIndex = 2*i+2;
        int root = arr[i];
        int maxIndex = leftChildIndex;
        if(hasRightChild(i,arr.length) && arr[rightChildIndex]>arr[leftChildIndex]) maxIndex = rightChildIndex;
        
        if(arr[maxIndex] > arr[i]) swap(arr,maxIndex,i);
    }
    
    private static void heapifyDown(int[] arr, int length){
        arr[0] = arr[length-1];
        int index = 0;
        
        while(hasLeftChild(index,length)){
            int lci = 2*index + 1;
            int rci = 2*index + 2;
            int maxIndex = lci;
            
            if(hasRightChild(index,length) && arr[rci]>arr[lci]) maxIndex = rci;
            
            if(arr[maxIndex] > arr[index]){
                swap(arr,maxIndex, index);
                index = maxIndex;
            } else break;
        }
    }
    
    
    public static int[] buildMaxHeap(int[] arr){
        // Find leaf nodes and ignore them because leaf nodes already satisfy heap property
        int firstLeafNodeIndex = arr.length/2;
        
        for(int i=firstLeafNodeIndex-1; i>=0;i--){
            heapify(arr,i);
        }
        
        return arr;
    }
    
    public static void sort(int[] arr){
        int n = arr.length;
        for(int i=n-1;i>=0;i--){
            System.out.print(arr[0]+ " ");
            heapifyDown(arr,i+1);
        }
    }
    
    
    public static int[] topKFrequent(int[] nums, int k){
        
        Map<Integer, Integer> map = new HashMap();
        
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        
        // notice the sorting order
        // sort by frequencey
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        
        for(int key: map.keySet()){
            pq.add(key);
            
            if(pq.size()>k) pq.poll();
        }
        
        int[] result = new int[k];
        int i=0;
        
        while(!pq.isEmpty()) result[i++] = pq.poll();
        
        return result;
    }
}
