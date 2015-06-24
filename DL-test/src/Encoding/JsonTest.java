package Encoding;

import net.sf.json.JSONObject;


public class JsonTest {
	public static void main(String[] args) {
		JSONObject j1 = new JSONObject();
		try {
			String a="\\dsaf";
			j1.put("test", a);
			System.out.println(j1.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
