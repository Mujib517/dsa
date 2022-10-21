package Stacks;

import java.util.Stack;

class QueueWithStacks {
    
    private Stack<Integer> s1, s2;
    private int count = 0;
    
    public QueueWithStacks() {
        s1 = new Stack();
        s2 = new Stack();
    }
    
    public void push(int x) {
        ++count;
        s1.push(x);
    }
    
    public int pop() {
        --count;
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        
        return s2.pop();
    }
    
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        
        return s2.peek();
    }
    
    public boolean empty() {
        return count == 0;
    }
}
