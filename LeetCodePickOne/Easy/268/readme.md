### 题目：长度为n的数组，所有元素非负且小于n且无序且无重复元素，求其缺少的元素 ###

#### 思路一 ####
> 先排序，再查找 ——O(NlogN+N)

        Arrays.sort(nums);
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=i)
				return i;
		}
		return nums.length;


#### 思路二 ####
> 前N个数和 - 数组和 ——O(n)

        int sum=0;
		for(int i=0;i<=nums.length;i++)
			sum+=i;
		int sums=0;
		for(int i=0;i<nums.length;i++)
			sums+=nums[i];
		return sum-sums;

- 想啥呢？为啥不（1+n）*n/2 .。。。。

#### 思路三 ####
> 一个一个找。。。O(n2)  faster than 1.16%

    public int missingNumber(int[] nums) {
       for(int i=0;i<nums.length;i++){
           if(!isexists(nums,i))
               return i;
       }
        return nums.length;
    }
    public boolean isexists(int[] nums,int x){
        for(int i=0;i<nums.length;i++){
            if(nums[i]==x)
                return true;
        }
        return false;
    }
