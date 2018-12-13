package fileClear;

import java.util.Comparator;

public class lineComparator implements Comparator<String> {
	@Override
	public int compare(String o1,String o2) {
		//System.out.println(o1+"\n"+o2);
		String timestamp1=o1.substring(o1.lastIndexOf(" ")+6, o1.length()-6);
		String timestamp2=o2.substring(o2.lastIndexOf(" ")+6, o2.length()-6);

		return Integer.parseInt(timestamp1)-Integer.parseInt(timestamp2);
	}

}
