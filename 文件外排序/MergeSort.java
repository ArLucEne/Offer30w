package fileClear;

import java.io.BufferedReader;   
import java.io.BufferedWriter;   
import java.io.File;   
import java.io.FileReader;   
import java.io.FileWriter;   
import java.text.SimpleDateFormat;   
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;   
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;   
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;   

import fileClear.FileInfo;
import fileClear.lineComparator;

/**  
 *   
 * 外部排序指的是大文件的排序，即待排序的记录存储在外存储器上，待排序的文件无法一次装入内存，  
 * 需要在内存和外部存储器之间进行多次数据交换，以达到排序整个文件的目的。  
 * 外部排序最常用的算法是多路归并排序，即将原文件分解成多个能够一次性装人内存的部分，  
 * 分别把每一部分调入内存完成排序。然后，对已经排序的子文件进行归并排序。  
 *   
 * @author jia.hej  
 *  
 * @version $Id: MergeSort.java, v 0.1 2009-8-7 下午03:53:51 jia.hej Exp $  
 */  
public class MergeSort {   
  
    /** 十 */  
    private static long   TEN              = 10;   
    /** 百 */  
    private static long   HUNDRED          = 100;   
    /** 千 */  
    private static long   THOUSAND         = 1000;   
    /** 万 */  
    private static long   MILLION          = 10000;                 //1078  00:00:01 078   
    /** 十万 */  
    private static long   TEN_MILLION      = 100000;                //9656  00:00:09 656   
    /** 百万 */  
    private static long   HUNDRED_MILLION  = 1000000;               //93733  00:01:33 733   
    /** 千万 */  
    private static long   THOUSAND_MILLION = 10000000;              //970144  00:16:10 144   
    /** 亿 */  
    private static long   BILLION          = 100000000;   
    /** 十亿 */  
    private static long   TEN_BILLION      = 1000000000;   
    /** 百亿 */  
    private static long   HUNDRED_BILLION  = 10000000000l;   
    /** 千亿 */  
    private static long   THOUSAND_BILLION = 100000000000l;   
  
    //private static String INPUT_FILE       = "C:\\Users\\Domain\\Desktop\\export.txt";   
   // private static String INPUT_FILE       = "C:\\Users\\Domain\\Desktop\\export_cleaned.txt";   
    private static String INPUT_FILE       = "C:\\Users\\Domain\\Desktop\\export_last.txt";   
    
    private static String OUTPUT_FILE      = "C:\\Users\\Domain\\Desktop\\output.txt";   
  
    /** 拆分大小 */  
    private static int    SPLIT_SIZE       = 10 * 10000;   
  
    private static int    numSize;   
  
    public static void main(String[] args) throws Exception {   
        createDir("c:\\test");   
        createFile(INPUT_FILE);   
        //numSize = 2975000;
        //numSize=31000;
        numSize=1376000;
        sortFile(INPUT_FILE);   
  
        long beginTime = System.currentTimeMillis();   
        System.out.println("begin=" + beginTime);   
  
        //拆分文件   
        splitFile(INPUT_FILE, numSize);   
  
        List<String> splitFilePathList = new ArrayList<String>();   
        File dir = new File("c:\\test\\temp");   
        File[] files = dir.listFiles();   
        for (int i = 0; i < files.length; i++) {   
            File file = files[i];   
            splitFilePathList.add(file.getAbsolutePath());   
        }   
        //合并文件   
        createFile(OUTPUT_FILE);   
        mergeFile(splitFilePathList, OUTPUT_FILE);   
  
        long endTime = System.currentTimeMillis();   
        System.out.println("end=" + endTime);   
        System.out.println("end-begin=" + (endTime - beginTime));   
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");   
        System.out.println(simpleDateFormat.format(endTime - beginTime));   
  
        //删除拆分文件   
        System.gc();   
        Runtime.getRuntime().exec(new String[] { "cmd", "/c", "del", "c:\\test\\temp\\*.txt" });   
    }   
  
    //对段文件排序
    private static void sortFile(String path) throws Exception {   
        //SortedSet<Integer> set = new TreeSet<Integer>();   
        File file = new File(path);   
        FileReader fileReader = new FileReader(file);   
        BufferedReader bufferedReader = new BufferedReader(fileReader);   
        String value;   
        List<String> list=new LinkedList<String>();
        while ((value = bufferedReader.readLine()) != null) {   
            //set.add(Integer.parseInt(value.substring(value.lastIndexOf(" ")+1,value.length()-6)));   
        	//map.put(value.substring(value.lastIndexOf(" ")+1, value.length()-6), value);
        	list.add(value);
        }   
        Collections.sort(list, new lineComparator());
        bufferedReader.close();   
        fileReader.close();   
        createFile("c:\\test\\input排序.txt");   
        writeFile("c:\\test\\input排序.txt", list, false);
        //list.clear();
    }   
  
    
    /**  
     * 拆分文件  
     *   
     * @param inputPath  
     * @param numSize  
     * @throws Exception  
     */  
    private static void splitFile(String inputPath, int numSize) throws Exception {   
        File file = new File(inputPath);   
        FileReader fileReader = new FileReader(file);   
        BufferedReader bufferedReader = new BufferedReader(fileReader);   
        //SortedSet<Integer> set = new TreeSet<Integer>();
        List<String> list=new LinkedList<String>();
        Collections.sort(list, new lineComparator());
        String str;   
        createDir("c:\\test\\temp");   
        if (numSize > SPLIT_SIZE) {   
            int count = 1;   
            int fileNum = 1;   
            while ((str = bufferedReader.readLine()) != null) {   
                list.add(str);   
                //超过拆分数，写入子文件   
                if (count >= SPLIT_SIZE) {   
                    createFile("c:\\test\\temp\\" + fileNum + ".txt");   
                    writeFile("c:\\test\\temp\\" + fileNum + ".txt", list, false);   
                    list.clear();   
                    count = 0;   
                    fileNum++;   
                }   
                count++;//读取文件当前行数   
            }   
        }   
        //总量未达到拆分数，写入子文件   
        else {   
            while ((str = bufferedReader.readLine()) != null) {   
                list.add(str);   
            }   
            createFile("c:\\test\\temp\\1.txt");   
            writeFile("c:\\test\\temp\\1.txt", list, false);   
        }   
        if (bufferedReader != null) {   
            bufferedReader.close();   
        }   
        if (fileReader != null) {   
            fileReader.close();   
        }   
    }   
  
    /**  
     * 合并文件  
     *   
     * <p>  
     *    1.txt（1、3、5、7、9）和2.txt（6、8、9）<br/>  
     *    首先1和6进入treeset。 <br/>  
     *    输出1，发现是来自于1.txt的，再读入3，此时set中的元素是6和3。<br/>   
     *    输出3，发现还是来自于1.txt的，再读入5，此时set中的元素是6和5。 <br/>  
     *    输出5，发现还是来自于1.txt的，再读入7，此时set中的元素是6和7。 <br/>  
     *    输出6，发现来自于2.txt的，读入8，此时set中的元素是8和7。 <br/>  
     *    输出7，发现来自于1.txt的，读入9，此时set中的元素是8和9。 <br/>  
     *    输出8，发现来自于2.txt的，读入9，此时set中的元素是9和9。  
     * </p>  
     *   
     * @param splitFilePathList  
     * @param outputPath  
     * @throws Exception  
     */  
    private static void mergeFile(List<String> splitFilePathList, String outputPath)   
                                                                                    throws Exception {   
        //fileInfo添加到set   
        //SortedSet<FileInfo> fileInfoSet = new TreeSet<FileInfo>(new FileInfoComparator());   
        List<FileInfo> fileInfolist=new LinkedList<FileInfo>();
        
        if (fileInfolist.isEmpty()) {   
            for (int i = 0; i < splitFilePathList.size(); i++) {   
                File file = new File(splitFilePathList.get(i));   
                FileReader fileReader = new FileReader(file);   
                BufferedReader bufferedReader = new BufferedReader(fileReader);   
  
                FileInfo fileInfo = new FileInfo();   
                String splitFilePath = splitFilePathList.get(i);   
                fileInfo.setFileNum(Integer.parseInt(splitFilePath.substring(splitFilePath   
                    .lastIndexOf("\\") + 1, splitFilePath.indexOf(".txt"))));//文件号   
                fileInfo.setReader(bufferedReader);//reader引用   
                String value = bufferedReader.readLine();   
                if (value != null) {   
                    fileInfo.setValue(value);//当前值   
                    fileInfo.setLineNum(fileInfo.getLineNum() + 1);//当前行号   
                    fileInfolist.add(fileInfo);   
                }   
            }   
        }   
        Collections.sort(fileInfolist, new FileInfoComparator());
        
        //Set<Integer> valueSet = new LinkedHashSet<Integer>();   
        List<String> valuelist=new LinkedList<String>();
        boolean isSplit = false;   
        int count = 1;   
        //输出set元素   
        while (!fileInfolist.isEmpty()) {   
            FileInfo currentFileInfo = fileInfolist.get(0);   
            valuelist.add(currentFileInfo.getValue());   
            //拆分批量写入文件   
            if (valuelist.size() >= SPLIT_SIZE) { 
            	Collections.sort(valuelist, new lineComparator());
                writeFile(outputPath, valuelist, true);   
                valuelist.clear();   
                isSplit = true;   
            }   
  
            //clone fileInfo   
            FileInfo nextFileInfo = new FileInfo();   
            nextFileInfo.setFileNum(currentFileInfo.getFileNum());   
            nextFileInfo.setLineNum(currentFileInfo.getLineNum());   
            nextFileInfo.setValue(currentFileInfo.getValue());   
            nextFileInfo.setReader(currentFileInfo.getReader());   
  
            boolean isSuccess = nextFileInfo.readNextValue();   
  
            //未到文件末尾，set中fileInfo重新排序   
            if (isSuccess) {   
                if (fileInfolist.remove(currentFileInfo)) {   
                    fileInfolist.add(nextFileInfo);   
                }   
            }   
            //已到文件末尾，从set中移除该fileInfo   
            else {   
                fileInfolist.remove(currentFileInfo);   
            }   
  
            System.out.println("----- MergeFile:" + count++ + " -----");   
            System.out.println("fileNum=" + currentFileInfo.getFileNum());   
            System.out.println("lineNum=" + currentFileInfo.getLineNum());   
            System.out.println("value=" + currentFileInfo.getValue());   
            System.out.println("----------------------------");   
        }   
  
        //从未拆分过则一次性写入文件   
        if (valuelist.size() > 0 && valuelist.size() < SPLIT_SIZE && !isSplit) {   
            writeFile(outputPath, valuelist, false);   
        }   
        //曾拆分过剩余部分写入文件   
        else if (valuelist.size() > 0 && valuelist.size() < SPLIT_SIZE && isSplit) {   
            writeFile(outputPath, valuelist, true);   
        }   
    }   
  
    /**  
     * 生成随机数  
     *   
     * @param numSize  
     * @return  
     * @throws Exception  
     */  
    /*
    private static int createRandomNum(long numSize) throws Exception {   
        Set<Integer> set = new LinkedHashSet<Integer>();   
        int count = 0;   
        boolean isSplit = false;   
        while (count < numSize) {   
            int num = (int) (Math.random() * numSize + 1);   
            if (set.add(num)) {   
                count++;   
            }   
            //拆分批量写入文件   
            if (set.size() >= SPLIT_SIZE) {   
                writeFile(INPUT_FILE, set, true);   
                set.clear();   
                isSplit = true;   
            }   
        }   
  
        //从未拆分过则一次写入文件   
        if (set.size() > 0 && set.size() < SPLIT_SIZE && !isSplit) {   
            writeFile(INPUT_FILE, set, false);   
        }   
        //曾拆分过剩余部分写入文件   
        else if (set.size() > 0 && set.size() < SPLIT_SIZE && isSplit) {   
            writeFile(INPUT_FILE, set, true);   
        }   
        return count;   
    }   
  */
    private static void createDir(String dirPath) {   
        File dir = new File(dirPath);   
        if (!dir.exists()) {   
            if (dir.mkdir()) {   
                System.out.println(dir.getName() + " is create.");   
            }   
        }   
    }   
    
    
  
    private static void createFile(String path) throws Exception {   
        File file = new File(path);   
        if (!file.exists()) {   
            if (file.createNewFile()) {   
                System.out.println(file.getName() + " is create.");   
            }   
        }   
    }   
  
	public static String formatTime(String temp) {
		String timechuo=temp.substring(temp.lastIndexOf(" ")+1, temp.length()-6);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(timechuo);
        Date date = new Date(lt);
        temp= simpleDateFormat.format(date)+"   "+temp;
        return temp;
	}
    
    private static void writeFile(String path, List<String> set, boolean isAppend) throws Exception {   
        File file = new File(path);   
        FileWriter fileWriter = new FileWriter(file, isAppend);// 第二个参数表示：是否为追加模   
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);   
        Collections.sort(set, new lineComparator());
        Iterator<String> iterator = set.iterator();   
        while (iterator.hasNext()) {   
        	String line=iterator.next();
        	line=formatTime(line);
            bufferedWriter.write(line);   
            bufferedWriter.newLine();   
        }   
        bufferedWriter.flush();   
        if (bufferedWriter != null) {   
            bufferedWriter.close();   
        }   
        if (fileWriter != null) {    
            fileWriter.close();   
        }   
    } 
    
    
    private static int compareTimeStamp(String tmsp1,String tmsp2) {
    	String tm1=tmsp1.substring(5, 12);
    	String tm2=tmsp2.substring(5, 12);
    	return Integer.parseInt(tm1)-Integer.parseInt(tm2);
    	
    }
}
