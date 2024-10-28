package com.java.test;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		String NameAndMobile = "yuh#13557382054";
		System.out.println(NameAndMobile.lastIndexOf( '#' ));
		System.out.println(NameAndMobile.indexOf('@'));
//		String str = new String("yuh#13557382054");
//		String xx[] = new String[2];
//		int i = 0;
//		for (String retval : str.split("#")) {
//			xx[i++] = new String(retval);
//			System.out.println(retval);
//		}
//		for (int j = 0; j < xx.length; j++) {
//			System.out.println(xx[j]);
//		}
//		String str2 = "yuh#13557382054";
//        String[] buff = str2.split("a");
//        System.out.println(buff[0]);
//        System.out.println(buff[1]);
        
        System.out.println(getRandomString(6));

	}
	
	 public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
}
