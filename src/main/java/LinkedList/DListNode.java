package LinkedList;

public class DListNode {
    public int val;
    public DListNode next;
    public DListNode prev;

    public DListNode(int val) {
        this.val = val;
    }

    public DListNode(int val, DListNode prev,  DListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}