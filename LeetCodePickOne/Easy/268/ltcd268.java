package easy;


/**
 *  长度为n的数组，所有元素小于n且其无序且无重复元素，求其缺少的元素
 * @author Domain
 *
 */
public class ltcd268 {
	public int MissNumber(int[] nums) {
		/*
		Arrays.sort(nums);
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=i)
				return i;
		}
		return nums.length;
		*/
		int sum=0;
		for(int i=0;i<=nums.length;i++)
			sum+=i;
		int sums=0;
		for(int i=0;i<nums.length;i++)
			sums+=nums[i];
		return sum-sums;
	}

	public static void main(String[] args) {
		ltcd268 test = new ltcd268();
		int[] nums={1,4,2,0};
		System.out.println(test.MissNumber(nums));
	}
}
