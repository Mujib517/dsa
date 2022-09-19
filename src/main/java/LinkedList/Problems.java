package LinkedList;

public class Problems {
    
    
    private int length(ListNode head){
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            ++count;
            temp = temp.next;
        }
        
        return count;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = length(head);
        
        if (n == count) return head.next;
        
        int countToTraverse = count - n;
        ListNode temp1 = head;
        while (countToTraverse > 1) {
            temp1 = temp1.next;
            --countToTraverse;
        }
        temp1.next = temp1.next.next;
        return head;
    }
    
    // optimized using two pointers each n nodes apart.
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode temp1 = head;
        ListNode temp2 = head;
        
        // fast forward 2nd pointer to n nodes
        while(temp2!=null & n>0){
            temp2 = temp2.next;
            --n;
        }
        
        // where n is equal to number of nodes
        if(temp2==null) return temp1.next;
        
        // traverse pointer1 and pointer2 one node at a time
        while(temp2.next!=null){
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        
        // remove the nth node from end
        temp1.next = temp1.next.next;
        
        return head;
    }
    
    public static ListNode reverse(ListNode head){
        ListNode temp = head;
        ListNode prev = null;
        
        while(temp!=null){
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        
        return prev;
    }
    
    public static ListNode reverseRec(ListNode head, ListNode prev){
        if(head==null) return  prev;
        
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        return reverseRec(next,prev);
    }
    
    public static ListNode reverseRec(ListNode head){
        return reverseRec(head,null);
    }
    
    public static ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        
        while(p1!=null && p2.next!=null){
            p1= p1.next;
            p2= p2.next.next;
        }
        return p1;
    }
}
