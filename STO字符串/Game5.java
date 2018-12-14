package SwordToOffer;

import java.util.ArrayList;


public class Game5 {
	/**
	 * 将字符串空格转成%20
	 * @param str
	 * @return
	 */
	public String changeString(String str) {
		String[] lists = str.split(" ");
		String targetStr="";
		for(int i=0;i<lists.length;i++) {
			targetStr+=lists[i]+"%20";
		}
		return targetStr.substring(0,targetStr.length()-3);
	}
	/**
	 * 将两个有序数组合并，不申请第三个数组
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] tiedTwoNums(int[] nums1,int[] nums2) {
		int[] nums3=new int[nums1.length+nums2.length];
		int i=nums1.length-1;
		int j=nums2.length-1;
		int k=nums3.length-1;
		while(k>=0) {
			if(nums1[i]>nums2[j]) {
				nums3[k]=nums1[i];
				if(i>0)
					i--;
			}else {
				nums3[k]=nums2[j];
				if(j>0)
					j--;
			}
			k--;
		}
		return nums3;
	}
	
	public static void main(String[] args) {
		Game5 test = new Game5();
		int[] num1= {1,4,5,6,7,8};
		int[] num2= {2,5,7};
		System.out.println(Game5.tiedTwoNums(num1,num2));
		System.out.println(test.changeString("hello world hhh"));
	}
}
