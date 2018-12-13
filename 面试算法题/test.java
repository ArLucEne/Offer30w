package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class test {

	public List getMaxN(ArrayList<Integer> nums,int N){
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		ArrayList<Entry<Integer,Integer>> numlist=new ArrayList<>();
		for(Integer temp:nums) {
			if(map.containsKey(temp)) {
				int count=map.get(temp);
				map.put(temp,count+1);
			}
			else
				map.put(temp, 1);
		}
		//TreeMap<Integer,Integer> trmp=new TreeMap(map);
		numlist.addAll(map.entrySet());
	
		
		System.out.println(numlist.toString());
		
		Collections.sort(numlist, new MapComparator2());
		
		System.out.println(numlist.toString());
		List<Integer> preNnum=new ArrayList<>();
		int length=numlist.size();
		for(int i=0;i<N;i++) {
			preNnum.add(numlist.get(length-1-i).getKey());
		}
		return preNnum;
		
	}
	
	
	class MapComparator2 implements Comparator<Map.Entry<Integer,Integer>>{

		@Override
		public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
			// TODO Auto-g2enerated method stub
			if(o1.getValue()>o2.getValue())
				return 1;
			else
				return -1;
		}
		
	}

	
	public static void main(String[] args) {
		
		Integer[] inputnums= {2,3,4,5,2,3,4,13,2,13,4,2,1,3,4,4};
		ArrayList<Integer> nums=new ArrayList<Integer>();
		Collections.addAll(nums, inputnums);
		test t=new test();
		System.out.println(t.getMaxN(nums,3).toString());
	}

}


