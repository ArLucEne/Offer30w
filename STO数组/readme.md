## 剑指Offer 2.3.1  数组查找 ##

### 从左到右从上到下依次递增的二维数组，查找是否包含给定整数 ###


> 思路：从右上角开始查找，i,j控制查找范围（理解为缩小范围后的数组的右上角元素坐标），若大于目标，列数减一；小于目标，行数加一


- 不必sub新数组 
- 控制好while行列范围


参考代码

    	public boolean isExist(int[][] nums,int high,int width,int n) {
		int i=width-1,j=0;
		while(i>-1&& j<high) {	//数组范围把握不准确
			int temp=nums[i][j];
			if(temp==n)
				return true;
			if(temp>n)
				i--;
			if(temp<n)
				j++;
		}
		return false;
	}

