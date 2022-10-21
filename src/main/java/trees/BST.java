
package trees;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BST {
    
    static class Node{
        int data;
        Node left;
        Node right;
        
        public Node(int data){
            this.data = data;
        }
    }
    
    private static void findKthLargest(Node root, PriorityQueue<Integer> pq) {
        if (root == null) {
            return;
        }
        
        pq.add(root.data);
        
        findKthLargest(root.left, pq);
        findKthLargest(root.right, pq);
    }
    
    // with extra space
    int findKthLargest(Node root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        
        findKthLargest(root, pq);
        
        int max = Integer.MIN_VALUE;
        while (k >= 1) {
            max = pq.poll();
            k--;
        }
        
        return max;
    }
    
    // without extra space
    // index is 1 based
    private static int visitedIndex, value;
    
    private static void findNode(Node root, int k) {
        if (root.right != null) {
            findNode(root.right, k);
        }
        visitedIndex++;
        if (visitedIndex == k) {
            value = root.data;
        }
        if (root.left != null) {
            findNode(root.left, k);
        }
    }
    
    public static int findKthLargestOptimized(Node root, int k) {
        visitedIndex = 0;
        value = 0;
        findNode(root, k);
        return value;
    }
    
    private static void findSmallestNode(Node root, int k) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            findSmallestNode(root.left, k);
        }
        visitedIndex++;
        if (visitedIndex == k) {
            value = root.data;
        }
        
        if (root.right != null) {
            findSmallestNode(root.right, k);
        }
    }
    
    public static  int findKthSmallest(Node root, int k) {
        visitedIndex = 0;
        value = 0;
        findSmallestNode(root,k);
        return value;
    }
    
    public static Node lca(Node root, Node p, Node q) {
        if(root==null) return null;
        if(root.data== p.data || root.data == q.data) return root;
        
        if(p.data < root.data && q.data < root.data) return lca(root.left,p,q);
        else if(p.data>root.data && q.data > root.data) return lca(root.right,p,q);
        
        return root;
    }
    
    private static boolean isBst(Node root, int min, int max) {
        if(root==null) return true;
        
        if(root.data< min || root.data > max) return false;
        
        return isBst(root.left,min,root.data)
                && isBst(root.right,root.data,max);
        
    }
    
    public static boolean isBst(Node root) {
        return isBst(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    
    private static Node find(Node root, Node p){
        Node temp = root;
        while(temp!=null){
            if(root.data == p.data) return root;
            if(root.data>p.data) temp = temp.left;
            else temp = temp.right;
        }
        
        return null;
    }
    
    private static Node[] findWithParent(Node root, Node p) {
        Node parent = null;
        Node temp = root;
        while (temp != null) {
            if (temp.data == p.data) {
                return new Node[]{parent, temp};
            }
            parent = temp;
            
            if (root.data > p.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        
        return new Node[]{parent, null};
    }
    
    
    public static Node findSuccessor(Node root, Node p) {
        Node[] res = findWithParent(root, p);
        Node parent = res[0];
        Node current = res[1];
        
        if (current == null) {
            return parent;
        }
        
        // case 1: has right sub tree
        if (current.right != null) {
            Node temp = current.right;
            while (temp != null && temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }
        return parent;
    }
    
    private static boolean pairExists(Node root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        
        int required = k - root.data;
        if (set.contains(required)) {
            return true;
        }
        
        set.add(root.data);
        return pairExists(root.left, k, set)
                || pairExists(root.right, k, set);
    }
    
    public static boolean pairExists(Node root, int k) {
        Set<Integer> set = new HashSet();
        return pairExists(root, k, set);
    }
    
    public String serialize(Node root) {
        Queue<Node> q = new LinkedList();
        q.add(root);
        
        String res="";
        
        while(!q.isEmpty()){
            Node temp = q.poll();
            if(temp==null) {
                res+="N ";
                continue;
            }
            
            res+= temp.data + " ";
            // add left and right children even if they are null
            // adding null values will make our serialize string a full binary tree
            q.add(temp.left);
            q.add(temp.right);
        }
        return res;
    }
    
    private static Node build(String[] arr, int i) {
        if (i >= arr.length) {
            return null;
        }
        if (arr[i].equals("N")) {
            return null;
        }
        
        Node root = new Node(Integer.parseInt(arr[i]));
        root.left = build(arr, 2 * i + 1);
        root.right = build(arr, 2 * i + 2);
        
        return root;
    }
    
    public static Node deserialize(String s) {
        String[] tokens = s.split("\\s+");
        return build(tokens, 0);
    }
    
    public static void inorder(Node root){
        if(root==null) return;
        
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    
    
    static class Info{
        int min;
        int max;
        boolean isBST;
        int size;
        
        public Info(){}
        
        public Info(boolean isBST,int min,int max,int size){
            this.isBST=isBST;
            this.min = min;
            this.max =max;
            this.size = size;
        }
    }
    
    private static Info largest(Node root){
        if (root == null) {
            return new Info(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        
        Info left = largest(root.left);
        Info right = largest(root.right);
        
        Info current = new Info();
        
        current.size = Math.max(left.size, right.size);
        
        if (left.isBST && right.isBST && root.data > left.max && root.data < right.min) {
            current.isBST = true;
            current.min = Math.min(root.data, left.min);
            current.max = Math.max(root.data, right.max);
            current.size = 1 + left.size + right.size;
        }
        
        return current;
    }
    
    public static int largestBST(Node root){
        return largest(root).size;
    }
    
    Node predecessor = null;
    
    public Node inorderPredecessor(Node root, Node p){
        if(root==null) return null;
        
        if(root.data == p.data){
            
            if(root.left!=null){
                Node temp = root.left;
                while(temp.right!=null) temp = temp.right;
                predecessor = temp;
                return temp;
            } else{
                return predecessor;
            }
        }
        if(root.data >p.data) return inorderPredecessor(root.left,p);
        
        predecessor = root;
        return inorderPredecessor(root.right,p);
    }
    
    public Node getPredecessor(Node root, Node p) {
        if (root == null)
            return null;
        Node ans=null;
        
        Node temp = root;
        while(temp!=null){
            
            if(temp.data > p.data) temp = temp.left;
            else if(temp.data < p.data) {
                temp= temp.right;
                /* make sure to capture root, this will be the inorder predecessor in case if node
                doesn't have a left sub tree. */
                ans = temp;
            }
            // node found
            else{
                Node temp2 = temp.left;
                // no left subtree so return earlier captured ans
                if(temp2==null) return ans;
                
                // node has left subtree find the maximum in the left sub tree and return
                while(temp2.right!=null) temp2 = temp2.right;
                return temp2;
            }
        }
        
        return ans;
    }
    
    public Node getSuccessor(Node root, Node p){
        if(root==null) return null;
        
        Node ans = null;
        Node temp = root;
        
        while(temp!=null){
            
            if(temp.data > p.data){
                // capture the root node, this will be our answer if the node doesn't have a right subtree
                ans = temp;
                temp = temp.left;
            }
            else if(temp.data < p.data) temp = temp.right;
            
            else{
                Node temp2 = temp.right;
                // case1: has right sub tree
                if(temp2!=null){
                    while(temp2.left!=null)
                        temp2 = temp2.left;
                    return temp2;
                }
                
                // case2: doesn't have a right sub tree, return the earlier captured ans.
                return ans;
                
            }
        }
        
        
        return ans;
    }
    
    
    private Node deleteHelper(Node root, Node parent){
        Node pre = parent, current=root;
        while(current.left!=null) {
            pre = parent;
            current = current.left;
        }
        
        deleteRec(pre,current.data);
        return current;
    }
    
    public Node deleteRec(Node root, int key){
        if(root==null) return null;
        
        if (root.data < key)
            root.right = deleteRec(root.right, key);
        else if(root.data > key)
            root.left = deleteRec(root.left, key);
        
        else {
            //case1: leaf node, no children to handle
            if(root.right == null && root.left == null) return null;
            // case2: has left child/subtree
            if(root.right == null && root.left !=null) return root.left;
            // case2: has right child/subtree
            if(root.left == null && root.right != null ) return root.right;
            
            // case3: has two children
            // get inorder predecessor
            Node temp = deleteHelper(root.right,root);
            root.data = temp.data;
            
            return root;
        }
        
        return root;
    }
    
    
    public static int findMinInRotatedSortedArray(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            
            int mid = low + (high - low) / 2;
            
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            
            if (nums[low] <= nums[mid] && nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
    
    private static Node toBST(int[] arr, int start, int end){
        if(start<0 || end>=arr.length || start>end) return null;
        
        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        root.left = toBST(arr,start,mid-1);
        root.right = toBST(arr,mid+1,end);
        
        return root;
    }
    
    public static Node toBST(int[] arr){
        return toBST(arr,0,arr.length-1);
    }
        
}
