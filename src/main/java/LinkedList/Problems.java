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
        
        while (p1 != null && p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }
    
    public static boolean isPalindrome(ListNode head) {
        ListNode temp1 = head;
        ListNode temp2 = middleNode(head);
        ListNode reversedTemp = reverse(temp2);
        
        while (reversedTemp != null) {
            if (temp1.val != reversedTemp.val) {
                return false;
            }
            temp1 = temp1.next;
            reversedTemp = reversedTemp.next;
        }
        
        return true;
    }
    
    // 1 -> 2 -> 3 -> 4 -> 5  output grouped by odd and even indexes: 1 -> 3 -> 5 -> 2 -> 4
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode even = head;
        ListNode odd = head.next;
        ListNode oddTemp = odd;
        
        while (even.next != null && odd.next != null) {
            even.next = even.next.next;
            odd.next = odd.next.next;
            
            even = even.next;
            odd = odd.next;
        }
        
        even.next = oddTemp;
        
        return head;
    }
    
    
    static int getSize(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            ++count;
        }
        
        return count;
    }
    
    // bubble sort
    public static ListNode sortList(ListNode head) {
        int n = getSize(head);
        
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            ListNode temp = head;
            
            while (temp != null && temp.next != null) {
                ListNode next = temp.next;
                if (temp.val > next.val) {
                    int t = temp.val;
                    temp.val = next.val;
                    next.val = t;
                    swapped = true;
                }
                temp = temp.next;
            }
            
            if (!swapped) {
                break;
            }
        }
        
        return head;
    }
    
    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode sorted = new ListNode(0);
        ListNode temp = sorted;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        
        while (l1 != null) {
            temp.next = l1;
            l1 = l1.next;
            temp = temp.next;
        }
        
        while (l2 != null) {
            temp.next = l2;
            l2 = l2.next;
            temp = temp.next;
        }
        
        return sorted.next;
    }
    
    public static ListNode mergeSort(ListNode head){
        if(head == null || head.next == null ) return head;
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode temp = slow;
        
        while(fast!=null && fast.next!=null){
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        temp.next = null;
        
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(slow);
        return merge(left,right);
    }
    
}
