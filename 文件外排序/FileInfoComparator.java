package fileClear;

import java.util.Comparator;  
import fileClear.FileInfo;

/**  
 *   
 * 文件比较器  
 *   
 * @author jia.hej  
 *  
 * @version $Id: FileInfoComparator.java, v 0.1 2009-8-7 下午01:42:05 jia.hej Exp $  
 */  
public class FileInfoComparator implements Comparator<FileInfo> {   
  
	@Override
    public int compare(FileInfo o1, FileInfo o2) {  
    	String timestamp1=getTimeStamp(o1);
    	String timestamp2=getTimeStamp(o2);
    	
    	
        if (timestamp1 != timestamp2) {   
            return Integer.parseInt(timestamp1) - Integer.parseInt(timestamp2);   
        }  
        //如果存在重复值则使用文件号比较   
        else {   
            return o1.getFileNum() - o2.getFileNum();   
        }   
    }   
    
    public String getTimeStamp(FileInfo fi) {
    	String line=fi.getValue();
    	String timestamp=line.substring(line.lastIndexOf(" ")+6,line.length()-6);
    	return timestamp;
    }
  
}  