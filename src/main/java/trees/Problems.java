package trees;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import LinkedList.DListNode;

public class Problems {
    
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


}
