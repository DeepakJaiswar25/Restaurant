package com.deepak.Restuarant;

import java.util.Arrays;

public class test2 {
	static boolean isAnagram(String a, String b) {
		   if(a.length()==b.length()) {
		   String str1= a.toLowerCase();
		   String str2=b.toLowerCase();
	       char[] A= str1.toCharArray();
	       System.out.println(A);
	       char[] B= str2.toCharArray();
	       Arrays.sort(A);
	       Arrays.sort(B);
	       if(Arrays.equals(A, B)) {
	    	   return true;
	       }
	       
		   }
	       return false;
	    }

	  public static void main(String[] args) {
	    
	       
	        String a = "Hello";
	        String b = "hello";
	        boolean ret = isAnagram(a, b);
	        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
	    }
	}
