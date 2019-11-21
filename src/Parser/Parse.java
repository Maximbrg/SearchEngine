package Parser;
import Parser.ParserRuls.MonthYearRule;
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
    public Parse(String pathToDoc)
    {

        this.l_singleWords = new ArrayList<String>();//A data structure that contains the initial words
        this.d_wordsCount = new ArrayList<String>();//A data structure that contains the final words after all the required tests


        try {
            Scanner s = new Scanner(new File("C:\\Users\\shach\\Desktop\\stopWords"));
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
        String [] textArray = pathToDoc.split(" ");
        for(int i=0;i<textArray.length;i++){
            l_singleWords.add(textArray[i]);
        }

        if(stemmer==null){// This is the first iteration
            stemmer = new Stemmer();
            hs_delimitors = new HashSet<Character>(Arrays.asList('\'','(','[','{',')',']','}',',','.',';','/','\\','-', // "-"
                    '#','!','?','*',':','`','|', '&','^','*','@', '"'));
            d_months= new ArrayList<String >( Arrays.asList("january","february","march","april","may","june","july","august",
                    "september","october","november","december","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"));






        }


    }


    // Parse the document according to the set of rules given to us in the assignment
    //public void parseDoc(Document doc)
    public ArrayList<String> parseDoc()
    {
        /*
        //deleteDelimitors
        this.l_singleWords.add("hello,");
        this.l_singleWords.add("shachar!");
        this.l_singleWords.add("/10");
        //checkRange
        this.l_singleWords.add("word-");
        this.l_singleWords.add("word");
        this.l_singleWords.add("10-");
        this.l_singleWords.add("10");
        this.l_singleWords.add("between");
        this.l_singleWords.add("10");
        this.l_singleWords.add("and");
        this.l_singleWords.add("20");
*/

        //checks MM-DD.
     //   this.l_singleWords.add("14");
        //this.l_singleWords.add("May");
        this.l_singleWords.add("June");
        this.l_singleWords.add("4");


        //checks YYYY-MM.
        this.l_singleWords.add("May");
        this.l_singleWords.add("1994");


        //checks precentageRepresentation.
        this.l_singleWords.add("6%");
        this.l_singleWords.add("10.6");
        this.l_singleWords.add("percent");
        this.l_singleWords.add("10.6");
        this.l_singleWords.add("percentage");
        index = 0;
        int [] results = new int[2];
        ArrayList<String> words = new ArrayList<>();
        results[0] = 0;
        results[1] = 0;
        MonthYearRule rDM = new MonthYearRule();
        deleteDelimitors();//The function removes all punctuation marks from all words in the repository before we start working with them.


        while (index < l_singleWords.size())
        {

            if (l_singleWords.get(index).equals(""))
            {
                index++;
                continue;
            }
            if(index+1 < l_singleWords.size()) {
            words.add(l_singleWords.get(index));
            words.add(l_singleWords.get(index+1));
                results = rDM.roleChecker(words, d_wordsCount);
                if (results[0] == 1) {
                    index += results[1];
                    words.clear();
                    continue;
                }
                words.clear();
            }
            index++;
        }
/*
        doc.d_wordsCount = this.d_wordsCount;
        this.d_wordsCount = new Dictionary<string, int>();
*/
        return d_wordsCount;
    }
    /// delete the delimitors from the start and the end of the string in the list
    private void deleteDelimitors()
    {
        for (int i = 0; i < l_singleWords.size(); i++){
            //for (int j = 0; j < l_singleWords.get(i).length(); j++){
            String word= l_singleWords.get(i);
            boolean change=false;
            String tempWord="";
            for (int k = 0;  k<word.length() ; k++){
                if(!(word.charAt(k)=='.'||word.charAt(k)==','||word.charAt(k)=='!'||word.charAt(k)=='?'||word.charAt(k)==':')){
                    tempWord= tempWord +word.charAt(k);
                }
                else {
                    change=true;
                }
            }
            if(change==true){
                //l_singleWords.get(i).indexOf(tempWord);
                Collections.replaceAll(l_singleWords, word, tempWord);
            }


        }
    }

    // This function checks if the next word we are parsing is a stopword, if it is there's no point going over it.
    // <returns>true whether the next word is a stopword, false otherwise</returns>
    private boolean checkStopWord()
    {
        String checkWord = l_singleWords.get(index).toLowerCase();
        if (hs_stopwords.contains(checkWord))
        {
            index++;
            return true;
        }
        return false;
    }
///  round to 2 digits after the dot

    public static double roundTheDigits(double toRound)
    {
        int help;
        help = (int)(toRound * 100);
        toRound = (double)help / 100;
        return toRound;
    }
    ///save the regular numbers in the dictionary as they sould


}





