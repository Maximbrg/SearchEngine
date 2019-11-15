package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


public class ReadFile {
    Dictionary<String, Article> Article_dictionary;

    public ReadFile() {
        Article_dictionary = new Hashtable<String, Article>();
    }

    public void readFile() {
        File folder = new File("C:\\Users\\pc\\Desktop\\corpus"); // ****  NEED TO CHANGE IT TO LOCAL ADDRESS ****
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) { // Entering to main folder
            File subfolder = new File("C:\\Users\\pc\\Desktop\\corpus\\" + file.getName());//+
            File[] listOfsubFiles = subfolder.listFiles();
            for (File subfile : listOfsubFiles) {
                if (subfile.isFile()) {
                    try {
                        BufferedReader in = new BufferedReader(new FileReader(subfile.toString()));
                        String str;
                        String buffer = "";
                        while ((str = in.readLine()) != null) { // reading the file line by line
                            buffer += str;
                            if (str.equals("</DOC>")) { // new Article
                                String DOCNO = buffer.substring(buffer.indexOf("<DOCNO>"), buffer.lastIndexOf("</DOCNO>")); // Unique key of the Article
                                DOCNO = DOCNO.replace("<DOCNO>", "");  //clear tag
                                Article aa = new Article(DOCNO, buffer); // load the rest of the article
                                Article_dictionary.put(DOCNO, aa); // add to dictionary
                                buffer = "";
                            }
                        }
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}