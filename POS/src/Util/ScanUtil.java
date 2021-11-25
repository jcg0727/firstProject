package Util;

import java.util.Scanner;

public class ScanUtil {
	private static Scanner sc = new Scanner(System.in); //Scanner sc도 메모리에 올려주기 위해 static을 붙임
	
	public static String nextLine(){
		return sc.nextLine();
	}
	public static String next() {
		return sc.next();
	}
	
	public static int nextInt(){
		return Integer.parseInt(sc.nextLine());
	}
	
}

