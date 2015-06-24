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

	// Դ�ļ���
	static String url1 = "E:\\daling\\app_m_access.log.2015-05-29\\app_m_access.log.2015-05-29";
	// Ŀ���ļ���
	static String url2 = "E:\\daling\\test1";
	//��ϸURL�ļ�
	static String url3 = "E:\\daling\\test2";
	//��Ҫ�ļ�
	static String url4="E:\\daling\\test3";

	public static void main(String[] args) throws IOException {
		// �½��ļ����������������л���
		FileInputStream input = new FileInputStream(url1);

		// �½��ļ���������������л���
		InputStreamReader isr = new InputStreamReader(input, "UTF-8"); // ָ����UTF-8�������
		BufferedReader dr = new BufferedReader(isr);
		
		FileOutputStream out = new FileOutputStream(url2);
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8"); // ָ����UTF-8�������
		BufferedWriter bw = new BufferedWriter(osw);
		
		FileOutputStream out1 = new FileOutputStream(url3);
		OutputStreamWriter osw1 = new OutputStreamWriter(out1, "UTF-8"); // ָ����UTF-8�������
		BufferedWriter bw1 = new BufferedWriter(osw1);
		
		FileOutputStream out2 = new FileOutputStream(url4);
		OutputStreamWriter osw2 = new OutputStreamWriter(out2, "UTF-8"); // ָ����UTF-8�������
		BufferedWriter bw2 = new BufferedWriter(osw2);
		String content;
		// ��������
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
			System.out.println("���ڶ�ȡ��"+m);
		}
		
		sortMapByKey1(allUrlMap) ;
		int total=0;
		int total1=0;
		
		int nums=1;//����ͳ��
		for(String oUrl:allUrlMap.keySet()){
			Map<String,List<String>> subUrlMap=allUrlMap.get(oUrl);
			sortMapByKey(subUrlMap);
			bw.write(oUrl + "���͵�URL\r\n");
			int i=0;
			for(String sUrl:subUrlMap.keySet()){
				bw.write("    "+sUrl+"���͵�URL\r\n");
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
				bw.write("    "+sUrl+"���͵�URL���У�"+k+"������\r\n");
				bw2.write(sUrl+"  ������"+k+"������ʼ��Ϊ��"+nums+"\r\n");
				nums+=k;
				i++;
			}
			total+=i;
			bw.write(oUrl + "���͵�URL���У�"+i+"������\r\n");
		}
		
		
		bw.write("=============�ܽ᣺===============\r\n");
		bw.write("�����˵�URL���У�"+allUrlMap.size()+"\r\n");
		bw.write("�����˵�URL���У�"+total+"\r\n");
		bw.write("��ͳ�ƣ�"+total1+"������\r\n");
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
