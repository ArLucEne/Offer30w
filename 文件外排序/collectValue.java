package fileClear;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class collectValue {
	
	private String sourcepath="C:\\Users\\Domain\\Desktop\\test.txt";
	
	private String outputpath = "C:\\Users\\Domain\\Desktop\\test_out.txt";
	
	public class dataInfo{
		private String id;
		
		private String name;
		
		private String value;
		
		private String timestramp;
		
		dataInfo(String id,String name,String value,String timestamp){
			this.id=id;
			this.name=name;
			this.value = value;
			this.timestramp = timestamp;
		}
		
		public String getId() {
			return this.id;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getTimestramp() {
			return this.timestramp;
		}

		/*
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer(this.id);
			for(Entry<String,String> temp:this.valuemap.entrySet()) {
				sb.append("  ").append(temp.getKey()).append(":").append(temp.getValue());
			}
			sb.append("   ").append(this.timestramp);
			return sb.toString();
		}
		*/
	}
	
	public dataInfo lineTodata(String line) {
		String timestramp = line.substring(0,line.indexOf("   "));
		System.out.println(timestramp);
		int start = line.indexOf("name__=")+1;
		int end = line.indexOf(",",start);
		String name = line.substring(start+6,end);
		
		start = line.indexOf("value",end+1);
		String id = line.substring(end+1,start);
		System.out.println(id);
		String value = line.substring(start+6,line.lastIndexOf(" "));
		System.out.println(name+"::::"+value);
		System.out.println("======");
		return new dataInfo(id,name,value,timestramp);
	}
	
	public String mapToString(LinkedHashMap<String,String> map) {
		StringBuffer sb = new StringBuffer();
		for(Entry<String,String> temp:map.entrySet()) {
			sb.append(temp.getKey());
			sb.append(":").append(temp.getValue()).append("  ");
		}
		sb.append("\n");
		return sb.toString();
	}
	public void collect() throws IOException {
		File file = new File(sourcepath);
		if(!file.exists())
			throw new FileNotFoundException();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		File outfile = new File(outputpath);
		if(!file.exists())
			file.createNewFile();
		FileOutputStream writer = new FileOutputStream(outfile);
		
		int count=0;
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		String line ;
		ArrayList<String> list = new ArrayList<String>();
		while((line=reader.readLine())!=null) {
			dataInfo data = lineTodata(line);
			if(!map.containsValue(data.getId())) {
				list.add(mapToString(map));
				count++;
				if(count==1000) {
					for(String str:list) 
						writer.write((str+"\n***************\n").getBytes("utf-8"));
					list.clear();
					count=0;
				}
				map.clear();
				map.put("id",data.getId());
				map.put("timestramp",data.getTimestramp());
				map.put(data.getName(),data.getValue());
			}else {
				map.put(data.getName(),data.getValue());
			}
		}
		for(String str:list) 
			writer.write((str+"***************\n").getBytes("utf-8"));
		
	}
	
	
	public static void main(String[] args) throws IOException {
		collectValue test = new collectValue();
		test.collect();
	}
	
}
