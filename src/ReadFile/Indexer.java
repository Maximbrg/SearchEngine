package ReadFile;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Responsible of building the inverted index for a corpus.
 */
public class Indexer {

    public Indexer() {

    }


    public void createInvertedIndex(TreeMap<String, LinkedList<String>> termDictionary, HashMap<String, String[]> docDictionary) throws IOException {

        //new File("C:\\Users\\shach\\Desktop\\SearchE\\CheckIndexer\\termFolder").mkdirs();
        String path = "C:\\Users\\pc\\Desktop\\test";
        File f = new File(path);
        if (!f.exists() && !f.isDirectory()) {
            path = "C:\\Users\\pc\\Desktop\\test\\A_C_Term.txt";
            File f1 = new File(path);
            f1.getParentFile().mkdirs();
            f1.createNewFile();

            File file = new File("C:\\Users\\pc\\Desktop\\test\\D_F_Term.txt");
            file.createNewFile();
            File file2 = new File("C:\\Users\\pc\\Desktop\\test\\G_J_Term.txt");
            file2.createNewFile();
            File file3 = new File("C:\\Users\\pc\\Desktop\\test\\K_M_Term.txt");
            file3.createNewFile();
            File file4 = new File("C:\\Users\\pc\\Desktop\\test\\N_R_Term.txt");
            file4.createNewFile();
            File file5 = new File("C:\\Users\\pc\\Desktop\\test\\S_Term.txt");
            file5.createNewFile();
            File file6 = new File("C:\\Users\\pc\\Desktop\\test\\T_V_Term.txt");
            file6.createNewFile();
            File file7 = new File("C:\\Users\\pc\\Desktop\\test\\W_Z_Term.txt");
            file7.createNewFile();
            File file8 = new File("C:\\Users\\pc\\Desktop\\test\\other_Term.txt");
            file8.createNewFile();
        }



        Set<String>  e = termDictionary.keySet();
        for(String str:e) {
            //String term = e.nextElement();
            saveTermInfo(termDictionary, str);
        }

        Set<String> e2 = docDictionary.keySet();
        for(String str:e2) {
        //    String doc = e2.nextElement();
            savetDocInfo(docDictionary,str);

        }

    }
    private String checkTerm(String term) {
        String termLowerCase=term.toLowerCase();
        char firsChar=term.charAt(0);
        String path="C:\\Users\\pc\\Desktop\\test\\";
        if(firsChar>='a'&&firsChar<='c')
            path+="A_C_Term";
        else if(firsChar>='d'&&firsChar<='f')
            path+="D_F_Term.txt";
        else if(firsChar>='g'&&firsChar<='j')
            path+="G_J_Term.txt";
        else if(firsChar>='k'&&firsChar<='m')
            path+="K_M_Term.txt";
        else if(firsChar>='n'&&firsChar<='r')
            path+="N_R_Term.txt";
        else if(firsChar=='s')
            path+="S_Term.txt";
        else if(firsChar>='n'&&firsChar<='r')
            path+="N_R_Term.txt";
        else if(firsChar>='t'&&firsChar<='v')
            path+="T_V_Term.txt";
        else if(firsChar>='w'&&firsChar<='z')
            path+="W_Z_Term.txt";
        else
            path+="other_Term.txt";
        return path;
    }


    private void saveTermInfo(TreeMap<String, LinkedList<String>> termDictionary,String term) {
        Writer writer = null;
        String Path =checkTerm(term);//check term and return the path we need
        //String Path = "C:\\Users\\shach\\Desktop\\SearchE\\CheckIndexer\\termsMainFile.txt";
        File f = new File(Path);

        //this is not the first iterate. termMainFile exists.
        if (f.exists() && !f.isDirectory()) {

            int lineNumberOfTheTerms = FileWordSearch(term, f);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < termDictionary.get(term).size(); i++) {
                sb.append(termDictionary.get(term).get(i) + ",");
            }
            if (lineNumberOfTheTerms!=-1) {
                //   lineNumberOfTheTerms=lineNumberOfTheTerms-1;
                AddNewTextToLine(f,lineNumberOfTheTerms,sb);
            } else {

                //need to add in the end of the text or in the sorting text.
                AddNewTextToEnd(f,term,termDictionary);
            }
        } else {

            //String nameFile = "termsMainFile";
            AddNewTextToEnd(f,term, termDictionary);
            //writeToFile(termDictionary, Path, term);

        }


    }


    ////func for saveTermInfo
    //return the number of the line of the term
    private int FileWordSearch(String word, File file) {

        try {
            String[] words = null;  //Intialize the word Array
            FileReader fr = new FileReader(file);  //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String s;
            String input = word;   // Input word to be searched
            int countLine = 0;   //Intialize the word to zero
            boolean flag=false;
            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split(" ");  //Split the word using space
                //for (String word : words)
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(input))   //Search for the given word
                    {
                        flag=true;
                        countLine=i;
                    }
                }

            }
            if(flag){
                return countLine;
            }
            else
                return -1;

        } catch (IOException ex) {
            // Report
        }

        return -1;
    }

    public void AddNewTextToEnd(File file,String term,TreeMap<String, LinkedList<String>> termDictionary) {

        StringBuilder sb = new StringBuilder();
        sb.append(term + " --> ");
        for (int i = 0; i < termDictionary.get(term).size(); i++) {
            sb.append(termDictionary.get(term).get(i) + " ");
        }
        String toAdd=sb.toString();

        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(toAdd);
            //more code

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        //after that all the file in lines array


    }



    //////////////////////////////////
    //////////////////////////////////
    public void AddNewTextToLine(File file, int lineNumberOfTheTerms,StringBuilder termToAdd) {


        int numLines=getNumberLines(file); //this will store the number of lines in the file
        String[] lines=new String[numLines]; //the lines of text that make up the file will be stored here
        for (int count = 0; count < numLines; count++) {
            lines[count] = readLine(count, file);//here we set each string in the array to be each new line of the file
        }

        //after that all the file in lines array

        addToEnd(lines,lineNumberOfTheTerms, termToAdd);
        try {

            writeFile(file,lines, numLines);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }


    }

    ///func to AddNewTextToLine
    public int getNumberLines(File aFile) {
        int numLines = 0;
        try {

            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                String line = null;

                while (( line = input.readLine()) != null){ //ReadLine returns the contents of the next line, and returns null at the end of the file.
                    numLines++;
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return numLines;
    }
    public String readLine(int lineNumber, File aFile) {
        String lineText = "";
        try {

            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                for(int count = 0; count < lineNumber; count++) {
                    input.readLine();  //ReadLine returns the contents of the next line, and returns null at the end of the file.
                }
                lineText = input.readLine();
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return lineText;
    }
    public void addToEnd(String[] lines, int lineNumberOfTheTerms, StringBuilder termToAdd) {
        try {
            String term= termToAdd.toString();
            lines[lineNumberOfTheTerms] = lines[lineNumberOfTheTerms].concat(term); //this joins the two strings lines[0] and " ddd"

        } catch (ArrayIndexOutOfBoundsException ex) { // I have added a try{}catch{} block so that if there is not as many lines in the file as expected, the code will still continue.

        }



    }
    public void writeFile(File aFile,String[] lines, int numLines ) throws FileNotFoundException, IOException {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            throw new FileNotFoundException ("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }

        BufferedWriter output = new BufferedWriter(new FileWriter(aFile));
        try {
            for(int count = 0; count < numLines; count++) {
                output.write(lines[count]);
                if(count != numLines-1) {// This makes sure that an extra new line is not inserted at the end of the file
                    output.newLine();
                }

            }

        }
        finally {
            output.close();
        }
    }

    /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////

    ////func for savetDocInfo
    private void savetDocInfo(HashMap<String, String[]> docDictionary ,String doc) {
        Writer writer = null;

        String Path = "C:\\Users\\pc\\Desktop\\documentFile.txt";
        File f = new File(Path);

        //this is not the first iterate. documentFile exists.
        if (f.exists() && !f.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            sb.append(doc + " --> ");
            for (int i = 0; i < docDictionary.get(doc).length; i++) {
                sb.append(docDictionary.get(doc)[i] + " ");
            }
            String toAdd=sb.toString() + "\n";

            try(FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(toAdd);
                //more code

            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }


        } else {

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("documentFile.txt"), "utf-8"));
                StringBuilder sb = new StringBuilder();
                sb.append(doc + " -->");
                System.out.println(docDictionary.get(doc)[0]);
                sb.append(docDictionary.get(doc)[0] + " ");
                sb.append(docDictionary.get(doc)[1] + " ");
                sb.append(docDictionary.get(doc)[2]+ " ");

                writer.write(sb.toString() + "\n");

            } catch (IOException ex) {
                // Report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/}
            }

        }
    }

    ////////////////////
    ///////////////////
    /*
    //create THE DICTIONARY!
    private void saveDictionary(String Path) {
        Writer writer = null;

        //String Path = "C:\\Users\\shach\\Desktop\\SearchE\\CheckIndexer\\documentFile.txt";
        File f = new File(Path);

        //this is not the first iterate. documentFile exists.
        if (f.exists() && !f.isDirectory()) {



        } else {

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("dictionary.txt"), "utf-8"));

                StringBuilder sb = new StringBuilder();
                sb.append( + " -->");


                writer.write(sb.toString() + "\n");

            } catch (IOException ex) {
                // Report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {}
            }

        }
    }

*/
}








/////////////////////////////////
//////////////////////////////////
/*    private void writeToFile(Dictionary<String, LinkedList<String>> termDictionary, String Path,String term) {
        Writer writer = null;
        try {

            writer = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream(Path + ".txt"), "utf-8"));

            StringBuilder sb = new StringBuilder();
            sb.append(term+ " --> ");
            for (int i = 0; i < termDictionary.get(term).size(); i++) {
                sb.append(termDictionary.get(term).get(i) + " ");
            }
            writer.write(sb.toString()+"\n");

        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {}
        }

    }
*/