
package trees;

import java.util.Stack;

class BSTIterator {
    
    private Stack<TreeNode> stack = new Stack();
    
    public BSTIterator(TreeNode root) {
        stack.push(root);
        addToStack(root.left);
    }
    
    public int next() {
        TreeNode temp = stack.pop();
        addToStack(temp.right);
        return temp.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void addToStack(TreeNode root) {
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }
}
