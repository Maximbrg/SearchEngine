package ReadFile;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.ArrayList;

public class Article  {
    private  static int i=0;
    String DANCO;
    String info;

    public Article(String DANCO, String info) {
        this.DANCO = DANCO;
        this.info = info;
        System.out.println(i+"---------------"+DANCO);
        i++;
}


    public String getText(){
        try {
            String text = info.substring(info.indexOf("<TEXT>"), info.lastIndexOf("</TEXT>")); // 11838
            text = text.replace("<TEXT>", "");
            return text;
        }
        catch (Exception e ){
            return "";
        }
    }
}
