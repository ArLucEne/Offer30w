### 题目：Given a linked list, determine if it has a cycle in it. ###

> Can you solve it using O(1) (i.e. constant) memory?

效率低下，使用set或者数组判断是否存在之前已访问过的节点


    public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode point = new ListNode(9527);
        point.next=head;
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        while(point.next!=null){
            if(list.contains(point.next))
                return true;
            list.add(point.next);
            point=point.next;
        }
        return false;
    }
    }

不使用额外空间，建立walker和runner两个节点，若存在环路，由于两节点遍历速度不同，总会在某个时刻相遇，！！！链表题中比较有效的方法

    public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    while(runner.next!=null && runner.next.next!=null) {
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
    }

==========================================
### 变例：Given a linked list, return the node where the cycle begins. If there is no cycle, return null. ###

> 不考虑空间复杂度，同样可以申请O(n)的arraylist判断是否存在相同节点；
> 否则，仍需要两个速度不同的节点，如果两节点相遇，测存在循环，从head重新出发一个节点，和另外两个节点的其中一个同时按步前进，相遇时的节点即为所求节点

    public class Solution {
            public ListNode detectCycle(ListNode head) {
                ListNode slow = head;
                ListNode fast = head;
        
                while (fast!=null && fast.next!=null){
                    fast = fast.next.next;
                    slow = slow.next;
                    
                    if (fast == slow){
                        ListNode slow2 = head; 
                        while (slow2 != slow){
                            slow = slow.next;
                            slow2 = slow2.next;
                        }
                        return slow;
                    }
                }
                return null;
            }
        }