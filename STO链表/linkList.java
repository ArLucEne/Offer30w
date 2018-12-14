package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

public class linkList {

	listNode head = null; //listNode head = new listNode()�������⣬�����ж�ͷ�ڵ��Ƿ�Ϊ��
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
			node=node.next;			//��Ȼ�����˵���һֱ��ӡͷ�ڵ�
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
	 * ��Ҫ����ָ�����ɾ������
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
	 * �����ӡ�����ǵݹ飬ջ
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
	 * �ݹ鵹���ӡ
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
	 *����ת�����������ڵ�
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
			 preNode.next=null;		//������ʼʱ���׶Ͽ���һֱѭ��
			 while(afterNode!=null) {
				 point.next=preNode;
				 preNode=point;
				 point=afterNode;
				 afterNode=point.next;
			 }
			 point.next=preNode;	//����
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
