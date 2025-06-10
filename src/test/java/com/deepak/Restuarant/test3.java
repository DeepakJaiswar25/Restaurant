package com.deepak.Restuarant;

public class test3 {
	
	 public static String getSmallestAndLargest(String s, int k) {
	        String smallest = s.substring(0,k);
	        String largest = s.substring(0,k);
	        for(int i=1;i<=s.length()-k;i++) {
	        	String substring =s.substring(i,i+k);
	        	System.out.println(substring);
	        	if(substring.compareTo(smallest)<0) {
	        		smallest=substring;
	        	}
	        	else if(substring.compareTo(largest)>0){
	        		largest= substring;
	        	}
//	        	
	        }
	        return smallest + "\n" + largest;
	    }
	public static void main(String[] h) {
		 String s = "welcometojava";
	        int k = 3;
	      
	        System.out.println(getSmallestAndLargest(s, k));
	

}
}
