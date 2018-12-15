package easy;

import java.util.ArrayList;

public class MyHashSet {
	ArrayList<Integer> list;
	
	public MyHashSet() {
		list=new ArrayList<Integer>();
	}
	
	public void add(int key) {
		if(!list.contains(key)) {
			list.add(key);
		}
	}
	
	public void remove(int key) {
			list.remove((Integer)key);
	}
	
	public boolean contains(int key) {
		return list.contains(key);
	}
	
	public static void main(String[] args) {
		MyHashSet hashSet = new MyHashSet();
		hashSet.add(1);         
		hashSet.add(2);         
		hashSet.contains(1);    // returns true
		hashSet.contains(3);    // returns false (not found)
		hashSet.add(2);          
		hashSet.contains(2);    // returns true
		hashSet.remove(2);          
		hashSet.contains(2);    // returns f
		
	}
}
