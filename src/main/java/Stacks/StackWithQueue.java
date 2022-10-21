package Stacks;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithQueue {
    
    
    private int count = 0;
    private Deque<Integer> q;
    
    public StackWithQueue() {
        q = new LinkedList();
    }
    
    public void push(int x) {
        q.add(x);
        ++count;
    }
    
    public int pop() {
        --count;
        return q.removeLast();
    }
    
    public int top() {
        return q.peekLast();
    }
    
    public boolean empty() {
        return count == 0;
    }
}
