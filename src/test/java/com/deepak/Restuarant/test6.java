package com.deepak.Restuarant;

import java.util.Base64;
import java.util.Random;

public class test6 {
	public static void main(String[] args) {
		
	
		String name = "Deepak Jaiswar";
		String[] names = name.split(" ");
		String fname=names[0];
		String lname= names[1];
		Random random =new Random();
		int nextLong = random.nextInt(0,100000000);
		System.out.println(nextLong);
		String EmployeeID= fname.charAt(0)+""+ lname.charAt(0)+""+nextLong;
		System.out.println(EmployeeID);
		
		
		String Password="Deep@123";

		String encodedString = Base64.getEncoder().encodeToString(Password.getBytes());
		System.out.println(encodedString);
		
		byte[] decode = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decode);
		System.out.println(decodedString);
		
	}

}
