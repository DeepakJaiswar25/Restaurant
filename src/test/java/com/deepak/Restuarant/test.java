package com.deepak.Restuarant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


class test{
	public static int b ;
    public static int h ;
    
    public static String findday(int month,int day ,int year) {
    	LocalDate date =LocalDate.of(year, month, day);
    	DayOfWeek dow= date.getDayOfWeek();
    	return dow.toString();
    }

  public static void main(String[] args) {
	  int length=0;
   String s = ",_! ! _@?'  !?_,'! '_'_@.... ''?. ?_ !?. ..!'!?@,?@',?_@'!, !!?_,@?,'@@',, !.? @@@@!!?' _, @???_,@ !_@!'!!!__!??'!. _. ,@! !.?'@,' '_!@@,'?' '?' ''_? '!!'?_?,._!..@_,_', ',',, !!__!_@,.! '?...  ?_? ._ ?' _! '?_..,,''.'@'??.??' @'.?' ?'_.'.'_ .'??@',_@_ , ?! , ._ . ',_'@..' ?,?,!_'',',,,?,..',.?,?_',..@?.,_ .@_?_ ,',.?_!'.??'_@__!!!'._!!__'? .?,._'', @@?!@ '?_ .@  .!!!' .'.', _!''!_@.@..@',@? @?. .@'.,@ , !'! @,@.@. ?,'_!,_'_,'! @_,_@._'?'',!_@ ,'?.@ _!__@'. '?_, ?@_,,.,.@',@!!!@ !?!@ '@_'_ _'  @_? ,_. .@@@ '.  '!@  ._ ,__!__', '._?.,,, ,!.,!?? ??. ?@_  ,@'_@'!.,@@. @@,, ?_??!' !,@'?@!!@@'_ ?'@!.@', !?.'@' _'! @ ?.,., ?' ''@ ?_ __! ! '! . ,.@,'?.?__'__@..@'!@.@ _  .!! !?_ _.?,  ''_._! ? !,_ ,, ,@!?@,@?,. _@ @_'?!._,'_?!,??.?@,.!!' .!..',._'?  '_,'?@ _!.,_,.!?_ ?,,'@!!?@ @@!@.?_'!_.?!@ ??!' ,,,.,!_?.?.,!_ _ _.,?!_.'_ ,@'? !_.?@!',.?_@ _? ? ,@!', .,??!@??? @,!_.@ !. ''!,'?  ?,!'_.,@,@!_@ ''_._@_!@.!'@ ?@!@!,_@@ . @@_.@_??!!!@, ??'!., _ !'@'_@?_.!, ''?? ..''.,,.?@@??'.,_?'?'.' '' @,?'''@@.";
//      String arr=s.replaceAll("[!_@.,?'']", " ");
   
     
      String[] arr2=s.split("[ !,?._'@]+");
     length=arr2.length;
      System.out.println(length);
      for(int i=0;i<arr2.length;i++){
          System.out.println(arr2[i]);
      }
  }
}

/*17
9223372036854775808
9223372036854775807
-9223372036854775808
-9223372036854775807
4294967296
4294967295
-4294967296
-4294967295
65536
65535
-65536
-65535
256
255
-256
-255
12222222222222222222222222222222222222222221
Expected Output
9223372036854775808 can't be fitted anywhere.
9223372036854775807 can be fitted in:
* long
-9223372036854775808 can be fitted in:
* long
-9223372036854775807 can be fitted in:
* long
4294967296 can be fitted in:
* long
4294967295 can be fitted in:
* long
-4294967296 can be fitted in:
* long
-4294967295 can be fitted in:
* long
65536 can be fitted in:
* int
* long
65535 can be fitted in:
* int{-truncated-}

 * 
 * 
 * 
 * 
 * 
 */

