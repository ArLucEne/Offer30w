package SwordToOffer;
/**
 * ��ά����Ĳ���
 * ��ά��������Ҵ��ϵ��µ��������Ƿ������n
 * @author Domain
 *
 */
public class Game4 {
	public boolean isExist(int[][] nums,int high,int width,int n) {
		int i=width-1,j=0;
		while(i>-1&& j<high) {	//���鷶Χ���ղ�׼ȷ
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
