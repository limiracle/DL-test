package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DelCommentsInJava {

	// 源文件夹
	static String url1 = "E:\\daling\\app_m_access.log.2015-05-29\\app_m_access.log.2015-05-29";
	// 目标文件夹
	static String url2 = "E:\\daling\\test1";
	//详细URL文件
	static String url3 = "E:\\daling\\test2";
	//概要文件
	static String url4="E:\\daling\\test3";

	public static void main(String[] args) throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(url1);

		// 新建文件输出流并对它进行缓冲
		InputStreamReader isr = new InputStreamReader(input, "UTF-8"); // 指定以UTF-8编码读入
		BufferedReader dr = new BufferedReader(isr);
		
		FileOutputStream out = new FileOutputStream(url2);
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8"); // 指定以UTF-8编码输出
		BufferedWriter bw = new BufferedWriter(osw);
		
		FileOutputStream out1 = new FileOutputStream(url3);
		OutputStreamWriter osw1 = new OutputStreamWriter(out1, "UTF-8"); // 指定以UTF-8编码输出
		BufferedWriter bw1 = new BufferedWriter(osw1);
		
		FileOutputStream out2 = new FileOutputStream(url4);
		OutputStreamWriter osw2 = new OutputStreamWriter(out2, "UTF-8"); // 指定以UTF-8编码输出
		BufferedWriter bw2 = new BufferedWriter(osw2);
		String content;
		// 缓冲数组
		content = dr.readLine();
		Map<String,Map<String,List<String>>> allUrlMap=new HashMap<String,Map<String,List<String>>>();
		int m=0;
		while (content != null) {
			m++;
			String[] conArr=content.split(" ");
			String url=conArr[6];
			String[] urlArr=url.split("/");
			if(urlArr.length<4){
				content = dr.readLine();
				continue;
			}
			String oUrl="/"+urlArr[1]+"/"+urlArr[2];
			String tUrl=urlArr[3];
			String sUrl="/"+urlArr[1]+"/"+urlArr[2]+"/";
			if(tUrl.contains("?")){
				sUrl+=tUrl.split("\\?")[0];
			}else{
				sUrl+=tUrl;
			}
			if(allUrlMap.containsKey(oUrl)){
				Map<String,List<String>> subUrlMap=allUrlMap.get(oUrl);
				if(subUrlMap.containsKey(sUrl)){
					subUrlMap.get(sUrl).add(url);
				}else{
					List<String> list=new LinkedList<String>();
					list.add(url);
					subUrlMap.put(sUrl, list);
				}
			}else{
				Map<String,List<String>> subUrlMap=new HashMap<String,List<String>>();
				List<String> list=new LinkedList<String>();
				list.add(url);
				subUrlMap.put(sUrl, list);
				allUrlMap.put(oUrl, subUrlMap);
			}
			content = dr.readLine();
			System.out.println("正在读取："+m);
		}
		
		sortMapByKey1(allUrlMap) ;
		int total=0;
		int total1=0;
		
		int nums=1;//行数统计
		for(String oUrl:allUrlMap.keySet()){
			Map<String,List<String>> subUrlMap=allUrlMap.get(oUrl);
			sortMapByKey(subUrlMap);
			bw.write(oUrl + "类型的URL\r\n");
			int i=0;
			for(String sUrl:subUrlMap.keySet()){
				bw.write("    "+sUrl+"类型的URL\r\n");
				List<String> list=subUrlMap.get(sUrl);
				int k=0;
				total1+=list.size();
				for(String value:list){
					if(k<2){
						bw.write("        "+value+"\r\n");
					}
					bw1.write(value+"\r\n");
					k++;
				}
				bw.write("    "+sUrl+"类型的URL共有："+k+"条数据\r\n");
				bw2.write(sUrl+"  数据有"+k+"条，开始行为："+nums+"\r\n");
				nums+=k;
				i++;
			}
			total+=i;
			bw.write(oUrl + "类型的URL共有："+i+"条数据\r\n");
		}
		
		
		bw.write("=============总结：===============\r\n");
		bw.write("二级菜单URL共有："+allUrlMap.size()+"\r\n");
		bw.write("三级菜单URL共有："+total+"\r\n");
		bw.write("共统计："+total1+"条数据\r\n");
		bw.close();
		bw1.close();
		bw2.close();
		osw.close();
		osw1.close();
		osw2.close();
		out.close();
		out1.close();
		out2.close();
		
		dr.close();
		isr.close();
		input.close();
		
		
		
		
		
	}
	public static Map<String, List<String>> sortMapByKey(Map<String,  List<String>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String,  List<String>> sortMap = new TreeMap<String,  List<String>>(new Comparator<String>(){
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		sortMap.putAll(map);
		return sortMap;
	} 
	
	public static Map<String, Map<String,List<String>>> sortMapByKey1(Map<String, Map<String,List<String>>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Map<String,List<String>>> sortMap = new TreeMap<String, Map<String,List<String>>>(new Comparator<String>(){
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		sortMap.putAll(map);
		return sortMap;
	} 
}
