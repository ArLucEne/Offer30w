### 题目：Remove Nth Node From End of List ###
### 删除倒数第N个节点   ###

> Given linked list: 1->2->3->4->5, and n = 2.

> After removing the second node from the end, the linked list becomes 1->2->3->5.

- 删除链表节点可以是给定序列或者给定值,两种方法存在较大差异.给定节点只需一个指针即可;给定值则需要一个指针和一个pre指针,point负责比较,pre负责将point删除

- 理解题意,此题为删除倒数第N个节点

- 需要判断两种情况 1.删除的节点是头节点,此时返回head.next;      2.删除其余节点,返回head
- 纯撸代码玩不可丢三落四,比如少分号或者拼写错误

         public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = linklength(head);
    
        if(!(length<n)){
            ListNode point=head;
            if(length-n==0)
                return point.next;
            else{
                for(int i=1;i<length-n;i++)
                    point=point.next;
                point.next=point.next.next;
                return head;
            }
        }
        return head;
        }
    
         public int linklength(ListNode head){
        ListNode pNode = new ListNode(0);
        pNode=head;
        int count=1;
        while(pNode.next!=null){
            pNode=pNode.next;
            count++;
        }
        return count;
        }
    


> 高亮代码？？？快节点慢节点？？？？？

    //还是走的快的点(fastNode)与走得慢的点(slowNode)路程差的问题
	public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headNode = new ListNode(9527);
        headNode.next = head;
        ListNode fastNode = headNode;
        ListNode slowNode = headNode;
        while(fastNode.next != null){
        	if(n <= 0)
        		slowNode = slowNode.next;
        	fastNode = fastNode.next;
        	n--;
        }
        if(slowNode.next != null)
        	slowNode.next = slowNode.next.next;
        return headNode.next;
    }