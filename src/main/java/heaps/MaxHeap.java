package heaps;

public class MaxHeap {

    private int[] arr = new int[20];
    private int size = 0;

    public int peek(){
        if(size == 0) throw new IllegalStateException();

        return arr[0];
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int item){
        arr[size++] = item;
        heapifyUp();
    }

    public int poll(){
       if(size==0) throw new IllegalStateException();

       int temp = arr[0];
       size--;
       heapifyDown();
       return temp;
    }

    private boolean hasRightChild(int index){
        int rci = 2*index + 2;
        return rci < size;
    }

    private boolean hasLeftChild(int index){
        int rci = 2*index + 1;
        return rci < size;
    }

    private void heapifyDown(){
        arr[0] = arr[size];
        int index = 0;

        while(hasLeftChild(index)){
            int lci = 2*index + 1;
            int rci = 2*index + 2;
            int maxIndex = lci;
    
            if(hasRightChild(index) && arr[rci]>arr[lci]) maxIndex = rci;
            
            if(arr[maxIndex] > arr[index]){
                swap(maxIndex, index);
                index = maxIndex;
            } else break;
        }
    }

    private void heapifyUp(){
        int index = size-1;
        int pIndex = (index-1)/2;
        while(hasParent(index) && arr[pIndex] < arr[index]){
            swap(pIndex, index);
            index = pIndex;
            pIndex = (index-1)/2;
        }
    }

    @Override
    public String toString(){
        String str="";
        for(int i=0;i<size;i++) str+=arr[i]+" ";

        return str;
    }

    private boolean hasParent(int index){
        return index != 0;
    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j]; 
        arr[j] = temp;
    }
}
