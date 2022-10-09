package trees;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import LinkedList.DListNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Problems {
    
    static class Pair {
        
        TreeNode node;
        int hd;
        
        public Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
    
    /* Traversals */
    public static void preOrder(TreeNode root){
        if(root==null) return;
        
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    
    public static void preOrderIterative(TreeNode root){
        if(root==null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            
            // insert right first and then left
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
        }
    }
    
    public static void inOrderIterative(TreeNode root){
        if(root==null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        
        while(temp!=null || !stack.isEmpty()){
            
            while(temp!=null){
                stack.push(temp);
                temp = temp.left;
            }
            
            temp = stack.pop();
            System.out.println(temp.val);
            temp = temp.right;
        }
    }
    
    public static void inOrder(TreeNode root){
        if(root==null) return;
        
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }
    
    public static void postOrder(TreeNode root){
        if(root==null) return;
        
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }
    
    public static void postOrderIterative(TreeNode root){
        if(root==null) return;
        
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        
        s1.push(root);
        
        while(!s1.isEmpty()){
            TreeNode temp = s1.pop();
            s2.push(temp);
            
            if(temp.left!=null) s1.push(temp.left);
            if(temp.right!=null) s1.push(temp.right);
        }
        
        while(!s2.isEmpty()) System.out.println(s2.pop().val);
    }
    
    private static boolean isLeaf(TreeNode root){
        return root.left == null && root.right == null;
    }
    
    public static void printLeaves(TreeNode root){
        if(root==null) return;
        
        if(isLeaf(root)) {
            System.out.print(root.val + " ");
            return;
        }
        
        printLeaves(root.left);
        printLeaves(root.right);
    }
    
    public static void levelOrder(TreeNode root){
        if(root==null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            System.out.print(node.val+" ");
            if(node.left!=null) q.add(node.left);
            if(node.right!=null) q.add(node.right);
        }
    }
    
    public static void levelOrder2(TreeNode root){
        if(root==null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node==null) {
                System.out.println();
                if(!q.isEmpty()) q.add(null);
            }
            else {
                System.out.print(node.val+" ");
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
    }
    
    public static void levelOrderReverse(TreeNode root){
        if(root==null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node==null) {
                System.out.println();
                if(!q.isEmpty()) q.add(null);
            }
            else {
                System.out.print(node.val+" ");
                if(node.right!=null) q.add(node.right);
                if(node.left!=null) q.add(node.left);
            }
        }
        
    }
    
    public static void leftView(TreeNode root){
        if(root==null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        System.out.print(root.val+ " ");
        
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node==null) {
                if(!q.isEmpty()){
                    q.add(null);
                    System.out.print(q.peek().val+" ");
                }
            }
            else {
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
    }
    
    public static void rightView(TreeNode root){
        if(root==null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        System.out.print(root.val+ " ");
        
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node==null) {
                if(!q.isEmpty()){
                    q.add(null);
                    System.out.print(q.peek().val+" ");
                }
            }
            else {
                if(node.right!=null) q.add(node.right);
                if(node.left!=null) q.add(node.left);
            }
        }
    }
    
    public static int floor(TreeNode root,int val){
        if(root==null) return Integer.MAX_VALUE;
        
        if(root.val == val) return root.val;
        if(root.val > val) return floor(root.left, val);
        
        int flVal = floor(root.right, val);
        return flVal <= val ? flVal:root.val;
    }
    
    public static int ceil(TreeNode root,int val){
        if(root==null) return -1;
        
        if(val==root.val) return val;
        if(val > root.val) return ceil(root.right, val);
        
        int cl = ceil(root.left, val);
        
        return cl >= val ? cl: root.val;
    }
    
    /* Other problems */
    public static boolean isBST(TreeNode root, int min, int max){
        if(root==null) return true;
        
        if(!(root.val < min && root.val > max)) return false;
        
        return isBST(root.left,min, root.val)
                && isBST(root.right,root.val, max);
    }
    
    public static int min(TreeNode root){
        if(root.left==null) return root.val;
        
        return min(root.left);
    }
    
    public static int max(TreeNode root){
        if(root.right==null) return root.val;
        
        return max(root.right);
    }
    
    public static int height(TreeNode root){
        // height of any empty tree can also be considered -1 in some cases
        // in that case if(root==null) return -1;
        if(root==null) return 0;
        
        return Math.max(height(root.left), height(root.right))+1;
    }
    
    private static TreeNode find(TreeNode root, int p){
        if(root==null) return null;
        if(root.val == p) return root;
        if(root.val < p) return find(root.right, p);
        
        return find(root.left, p);
    }
    
    public static int findPredecessor(TreeNode root, int p) {
        TreeNode node = find(root,p);
        if(node.left==null) return Integer.MIN_VALUE;
        return max(node.left);
    }
    
    private static DListNode head = new DListNode(-1);
    private static DListNode temp = head;
    
    private static void toDLL(TreeNode root){
        if(root==null) return;
        
        toDLL(root.left);
        
        toDLL(root.right);
        
        DListNode item = new DListNode(root.val);
        temp.next = item;
        item.prev = temp;
        
        temp = item;
    }
    
    public static DListNode binaryToDLL(TreeNode root){
        toDLL(root);
        return head.next;
    }
    
    public static TreeNode connect(TreeNode root) {
        if(root==null) return null;
        
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            TreeNode nd = q.poll();
            if(nd!=null){
                if(nd.left!=null) {
                    q.add(nd.left);
                    nd.left.next = nd.right;
                }
                if(nd.right!=null) {
                    q.add(nd.right);
                }
            }else{
                if(!q.isEmpty()) q.add(null);
            }
        }
        
        return root;
    }
    
    public static void flatten(TreeNode root){
        if(root==null) return;
        
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        root.right= left;
        
        TreeNode temp = root;
        while(temp.right!=null) temp = temp.right;
        
        temp.right = right;
        
        flatten(root.right);
    }
    
    public static int[] topView(TreeNode root) {
        Queue<Pair> q = new LinkedList();
        Map<Integer, Integer> map = new TreeMap();
        q.add(new Pair(root, 0));
        
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            
            if (!map.containsKey(pair.hd)) {
                map.put(pair.hd, pair.node.val);
            }
            if (pair.node.left != null) {
                q.add(new Pair(pair.node.left, pair.hd - 1));
            }
            if (pair.node.right != null) {
                q.add(new Pair(pair.node.right, pair.hd + 1));
            }
        }
        
        int[] res = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i++] = entry.getValue();
        }
        
        return res;
    }
    
    
    // Diameter unoptimized
    /*
    
    Diameter can be in
    1. left sub tree
    2. right sub tree
    3. or through root node
    */
    public static int getDiameter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int diameterLeft = getDiameter(root.left);
        int diameterRight = getDiameter(root.right);
        int diameterThroughRoot = height(root.left) + height(root.right);
        
        return Math.max(diameterLeft, Math.max(diameterRight, diameterThroughRoot));
    }
    
    static class TreeInfo{
        int height;
        int diameter;
        
        TreeInfo(int height, int diameter){
            this.height = height;
            this.diameter = diameter;
        }
    }
    
    // calculate height and diameter in one call as opposed to separate calls
    
    public static TreeInfo diameterOptimized(TreeNode root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }
        
        TreeInfo left = diameterOptimized(root.left);
        TreeInfo right = diameterOptimized(root.right);
        int height = 1 + Math.max(left.height, right.height);
        
        int diameter = Math.max(left.diameter, Math.max(right.diameter, left.height + right.height));
        
        return new TreeInfo(height, diameter);
    }
    
    int preOrderIndex = 0;
    Map<Integer, Integer> map;
    
    private TreeNode build(int[] in, int[] pre, int i, int j) {
        if (i > j) {
            return null;
        }
        
        TreeNode root = new TreeNode(pre[preOrderIndex++]);
        if (i == j) {
            return root;
        }
        
        int index = map.get(root.val);
        
        root.left = build(in, pre, i, index - 1);
        root.right = build(in, pre, index + 1, j);
        
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        return build(inorder, preorder, 0, preorder.length - 1);
    }
    
    // height balanced binary trees
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        int lh = height(root.left);
        int rh = height(root.right);
        
        return Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    
    
    private static int pathSumUtil(TreeNode root, int sum){
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val == sum) {
            res++;
        }
        res += pathSumUtil(root.left, sum - root.val);
        res += pathSumUtil(root.right, sum - root.val);
        return res;
    }
    
    static int count=0;
    static List<Integer> list = new ArrayList();
    
    
    private static void getSum(TreeNode root, int sum){
        list.add(root.val);
        
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        
        int temp = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            temp += list.get(i);
            if (temp == sum){
                temp = 0;
                ++count;
            }
        }
        
//        list.remove(list.size() - 1);
        
    }
    
    private static int pathSum(TreeNode root, int sum) {
        getSum(root,sum);
        return count;
    }
    
    
}
