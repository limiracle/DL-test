package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {
	public static void main(String[] args) {
		String input="{\"suitableSkin\":[\"混合性\"],\"goodsId\":106356,\"effect\":[null],\"suitablePeople\":[\"女性\"],\"skuCode\":\"040081171A241\",\"suitableSeason\":[\"四季\"]}";
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(input);

			Map result = new HashMap();
			Iterator iterator = jsonObject.keys();
			String key = null;
			String value = null;
			String goodsId="";
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				String val = jsonObject.getString(key);
				if("goodsId".equals(key)){
					goodsId=val;
					continue;
				}
				if("".equals(val)){
					continue;
				}else if(val.startsWith("[\"")){
					val=val.substring(2,val.length()-2);
					if(!"".equals(val)&&!"".equals(val)){
						String[] valArr=val.split(",");
						for(String e:valArr){
							e=e.replace("\"", "");
							System.out.println("goodsId:"+goodsId+",tag:"+e);
						}
					}
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
