package Parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class CorpusDictenory {
    private static CorpusDictenory ourInstance = new CorpusDictenory();
    private Map<String, LinkedList<String>> Dictenory;
    private int counter=0;

    public static CorpusDictenory getInstance() {
        return ourInstance;
    }

    private CorpusDictenory() {
        Dictenory = new LinkedHashMap<>();
    }

    public void addNumber(String word,String articleKey){

        if(Dictenory.containsKey(word)){
            LinkedList<String> tmp = Dictenory.get(word);
            tmp.add(articleKey);
            Dictenory.replace(word,Dictenory.get(word),tmp);
            counter++;
        }
        else{
            LinkedList<String> tmp = new LinkedList<>();
            tmp.add(articleKey);
            Dictenory.put(word,tmp);
            counter++;
        }

    }
    public void addWord(String word,String articleKey) {

        String tempWord="";
        if(word.length()<1)
            return;
        if(word.charAt(0)>='A'&&word.charAt(0)<='Z'){
            word=word.toUpperCase();
           tempWord = word.toLowerCase();
        }
        else {
            word = word.toLowerCase();
             tempWord = word.toUpperCase();
        }

        if(Dictenory.containsKey(word)){
            LinkedList<String> tmp = Dictenory.get(word);
            tmp.add(articleKey);
            Dictenory.replace(word,Dictenory.get(word),tmp);
            counter++;
        }
        else{
            if (Dictenory.containsKey(tempWord)) {
                if(word.charAt(0)>='A'&&word.charAt(0)<='Z'){
                    LinkedList<String> tmp = Dictenory.get(tempWord);
                    tmp.add(articleKey);
                    Dictenory.replace(tempWord,Dictenory.get(tempWord),tmp);
                    counter++;
                }
                else{
                    LinkedList<String> tmp = Dictenory.get(tempWord);
                    tmp.add(articleKey);
                    Dictenory.remove(tempWord);
                   Dictenory.put(word, tmp);
                    counter++;
                }
            }
            else {
                LinkedList<String> tmp = new LinkedList<>();
                tmp.add(articleKey);
               Dictenory.put(word, tmp);
                counter++;
            }
        }


    }

    public  void printsize(){
        System.out.println(Dictenory.size());
    }

    private  void saveToDisck() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\pc\\Desktop\\corpus\\Dictenory");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(Dictenory);
        objectOut.close();
        System.out.println("The Object  was succesfully written to a file");
    }

}
