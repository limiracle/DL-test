package Encoding;

import java.io.UnsupportedEncodingException;

public class Test {

	/**
	 * ClassName: Test.java
	 * @Description: TODO
	 * @author ¿Ó÷æ
	 * @date 2015-6-23
	 * @param
	 * @return
	 * @Exception
	 */

	public static void main(String[] args) {

		try {
			System.out.println(new String("≤‚ ‘".getBytes("gb2312"),"gb2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
