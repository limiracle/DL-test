package test;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		String a="/api/activity/unReceiveCoupon";
		System.out.println(Arrays.toString(a.split("/")));
		String b="dsafdsfe";
		System.out.println(b.contains("?"));
		System.out.println(Arrays.toString(b.split("\\?")));
	}
}
