import Parser.*;
import ReadFile.ReadFile;

import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static  void main(String[]args){
   //ReadFile aa = new ReadFile();
   //aa.readFile();
     Parse par  = new Parse("June 74 $450,000 22 3/4 Dollars 1.732 Dollars $450,000,000 1,000,000 Dollars,");//

    par.parseDoc("");
        CorpusDictenory aaa = CorpusDictenory.getInstance();
        System.out.println("sss");
    }
}
