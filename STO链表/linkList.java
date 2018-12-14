package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

public class linkList {

	listNode head = null; //listNode head = new listNode()存在问题，不能判断头节点是否为空
	public class listNode{
		String value;
		listNode next;
		listNode(String value){
			this.value=value;
		}
		listNode(){
			this.value=null;
			this.next=null;
		};
		
	}
	
	
	public void print() {
		listNode node=this.head;
		if(node==null) {
			return;
		}
		while(node.next!=null) {
			System.out.print(node.value+"->");
			node=node.next;			//竟然忘记了导致一直打印头节点
		}
		System.out.println(node.value);

	}
	
	public void addNode(String value) {
		listNode node = new listNode();
		node.value=value;
		node.next=null;
		
		if(this.head==null) {
			this.head=node;
		}else {
			listNode pNode=this.head;
			while(pNode.next!=null) {
				pNode=pNode.next;
			}
			pNode.next=node;
		}
	}
	
	public boolean isExist(String value) {
		listNode pNode=this.head;
		while(pNode.next!=null ) {
			if(pNode.value==value) {
				return true;
			}else {
				pNode=pNode.next;
			}
		}
		return false;
	}
	/**
	 * 需要两个指针控制删除过程
	 * @param value
	 * @return
	 */
	public boolean deleteNode(String value) {
		if(this.head.value==value) {
			this.head=this.head.next;
			return true;
		}else {
			listNode preNode=this.head;
			listNode pNode=preNode.next;
			while(preNode.next!=null) {
				if(pNode.value==value) {
					preNode.next=pNode.next;
					return true;
				}else {
					preNode=pNode;
					pNode=pNode.next;
				}
			}
		}
		System.err.println("not found");
		return false;	
	}
	/**
	 * 逆序打印链表，非递归，栈
	 */
	public void printFromTail() {
		if(head==null) {
			System.err.println("link null");
		}else {
			Stack<String> stack = new Stack<>();
			listNode pNode=head;
			while(pNode!=null) {
				stack.push(pNode.value);
				pNode=pNode.next;
			}
			while(!stack.isEmpty()) {
				System.out.print(stack.pop()+"->");
			}
			System.out.println();
		}
	}
	
	/**
	 * 递归倒序打印
	 */
	public void printFromTail2() {
		printnode(head);
	}
	public void printnode(listNode node) {
		if(node.next!=null)
			printnode(node.next);
		System.out.print(node.value+"->");
	}
	
	/**
	 *链表反转，至少三个节点
	 */
	public void reverseLink() {
		 if(head==null)
			 System.err.println("null link");
		 if(head.next==null)
			 return;
		 else {
			 listNode preNode=head;
			 listNode point=head.next;
			 listNode afterNode=point.next;
			 preNode.next=null;		//不降开始时链首断开会一直循环
			 while(afterNode!=null) {
				 point.next=preNode;
				 preNode=point;
				 point=afterNode;
				 afterNode=point.next;
			 }
			 point.next=preNode;	//！！
			 head=point;
		 }
	}
	
	public static void main(String[] args) {
		linkList list =new linkList();
		list.addNode("hello");
		list.addNode("world");
		list.addNode("hhh");
		
		list.print();
		
		//list.deleteNode("world");
		//list.print();
		
		//list.deleteNode("world");
		//list.print();
		
		//list.printFromTail();
		
		//list.printFromTail2();
		list.reverseLink();
		list.print();
	}
	
}
