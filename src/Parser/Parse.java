package Parser;
import Parser.ParserRuls.*;
import Stemmer.Stemmer;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.text.Document;
import java.io.IOException;
import java.util.*;
import java.lang.String;
import java.io.File;

public class Parse {

    private static Stemmer stemmer;
    private static ArrayList<String> d_months;
    private static HashSet<Character> hs_delimitors;
    private static ArrayList<String> hs_stopwords;

    private Document doc;

    //change to hashset
    public ArrayList<String> d_wordsCount;

    public ArrayList<String> l_singleWords;
    private int index;


    //The class's constructor
    public Parse(String txt)
    {
        this.l_singleWords = new ArrayList<String>();//A data structure that contains the initial words
        this.d_wordsCount = new ArrayList<String>();//A data structure that contains the final words after all the required tests
        try {
            Scanner s = new Scanner(new File("C:\\Users\\pc\\Desktop\\corpus\\stopWords.txt"));
            hs_stopwords = new ArrayList<String>();
            while (s.hasNext()){
                hs_stopwords.add(s.next());
            }
            s.close();
        }
        catch (IOException e) {
            // Handle a potential exception
        }

        char[] tempChar= new char[] { ' ', '\t', '\n' };
        txt=txt.replaceAll("\n","");
        txt=txt.replaceAll("\"",""); // Rule
        String [] textArray = txt.split(" ");
        for(int i=0;i<textArray.length;i++){
            if(!textArray[i].equals(""))
            l_singleWords.add(textArray[i]);
        }
    }
    // Parse the document according to the set of rules given to us in the assignment
    //public void parseDoc(Document doc)
    public ArrayList<String> parseDoc(String articleKey)
    {
        index = 0;
        int [] results = new int[2];
        ArrayList<String> words = new ArrayList<>();
        boolean ifFound = false;
        String [] rulesToCheack = {"MothYearRule","DayMonthRule","PriceRepresentationRule","PrecentageRepresentationRole","RangeRule","NumberRepresentationRule","SingleWordRule"};
        deleteDelimitors();//The function removes all punctuation marks from all words in the repository before we start working with them.
        while (index < l_singleWords.size()) {
         if(checkStopWord())
          continue;
         if(l_singleWords.get(index).equals("")){
             index++;
             continue;
         }
            ifFound=false;
            results[0] = 0;
            results[1] = 0;
            for(int i=0;i<rulesToCheack.length;i++){
                words.clear();
                IRuleChecker Rulechecker = RulesFactory.getRuleChecker(rulesToCheack[i]);
                for(int j=0;j<4;j++){
                    if (index + j < l_singleWords.size())
                        words.add(l_singleWords.get(index + j));
                }
                results = Rulechecker.roleChecker(words,articleKey);
                if (results[0] == 1) {
                    index += results[1];
                    ifFound=true;
                    break;
                }
            }
            if(ifFound)
                continue;
            else{
                CorpusDictenory dictenory= CorpusDictenory.getInstance();
                dictenory.addWord(l_singleWords.get(index),articleKey);
                index++;
            }
        }
        return d_wordsCount;
    }
    /// delete the delimitors from the start and the end of the string in the list
    private void deleteDelimitors()
    {
        for (int i = 0; i < l_singleWords.size(); i++){
            String word= l_singleWords.remove(i);
            if(word.endsWith(",")||word.endsWith(".")||word.endsWith(")")||word.endsWith("("))
            {
                word = word.substring(0,word.length() - 1);
            }
            if(word.startsWith("(")||word.startsWith(")"))
                word = word.substring(1);
            int wordLength = word.length();
            if(word.startsWith("-")){
                while(word.startsWith("-")&&!word.isEmpty()){
                    word = word.substring(1);
                }
            }
            /*
            for(int j=0;j<wordLength;j++){
                if(word.charAt(j)=='('||word.charAt(j)==')'||word.charAt(j)=='*'||word.charAt(j)=='?')
                    word = word.substring(0, j) + word.substring(j+1);
                if(j+1<word.length()&word.charAt(j)=='-'&&!((word.charAt(j)>='a'&&word.charAt(j)<='z')||(word.charAt(j)>='A'&&word.charAt(j)<='Z')||(word.charAt(j)>='0'&&word.charAt(j)<='9'))){
                    word = word.substring(0, j) + word.substring(j+1);
                }
            }
            */
            l_singleWords.add(i,word);
        }
    }

    // This function checks if the next word we are parsing is a stopword, if it is there's no point going over it.
    // <returns>true whether the next word is a stopword, false otherwise</returns>
    private boolean checkStopWord()
    {
        String checkWord = l_singleWords.get(index).toLowerCase(); // problem with May and may
        /*
        if(index+1<l_singleWords.size()) {
            String nextWord = l_singleWords.get(index+1).toLowerCase();
            if (hs_stopwords.contains(checkWord)&&hs_stopwords.contains(nextWord)) {
                l_singleWords.remove(index+1);
                l_singleWords.remove(index);
                l_singleWords.add(index,checkWord+""+nextWord);
                return true;
            }
        }
        */
        if (hs_stopwords.contains(checkWord)) {
                index = index + 1;
                return true;
            }
        return false;
    }
}





