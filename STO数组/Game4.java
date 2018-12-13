package SwordToOffer;
/**
 * 二维数组的查找
 * 二维数组从左到右从上到下递增，求是否存在数n
 * @author Domain
 *
 */
public class Game4 {
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

	public static void main(String[] args) {
		Game4 test=new Game4();
		int[][] nums= {{1,2,8,9},{2,4,9,12},{4,5,10,13},{6,8,11,15}};
		System.out.println(test.isExist(nums,4,4,6));
	}
}
