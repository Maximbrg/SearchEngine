import Parser.*;
import ReadFile.ReadFile;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static  void main(String[]args)  {
        ReadFile aa = new ReadFile();
        long a= System.nanoTime();
        aa.readFile();
        long b= System.nanoTime();
   //Parse par  = new Parse("10,123 123 Thousand 1010.56 10,123,000 55 Million 10,123,000,000 55 Billion 6% 10.6 percent  10.6 percentage 14 June, 14 June June 4, JUNE 4 May 1994, MAY 1994 Word-word Word-word-word 5-word Word-10 5-9 Between 5 and 4 ");//
      //Parse par  = new Parse("89.144"); // not work
//par.parseDoc("");
        System.out.println("time"+"----"+(b-a)*0.000000001);
 CorpusDictenory aaa = CorpusDictenory.getInstance();
 aaa.printsize();
        TreeMap<String, LinkedList<String> > aabb = aaa.getDictenory();
 try {
     PrintWriter writer = new PrintWriter("AAAAAAA.txt", "UTF-8");
     for(String strt : aaa.Dictenory.keySet()){
         writer.println(strt);
     }
     writer.close();
 }
 catch (Exception e){
     System.out.println("sss");
 }

   //    Pattern p = Pattern.compile("[\\d]{4,6}[m]");//. represents single character
   //   Matcher m = p.matcher("9999m");
    //   System.out.println(m.matches());

    }
}
