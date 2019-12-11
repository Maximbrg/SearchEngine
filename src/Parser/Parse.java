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

import static jdk.nashorn.internal.runtime.JSType.isNumber;

public class Parse {
    private static Parse ourInstance = new Parse();
    private static Stemmer stemmer;
    private static ArrayList<String> d_months;
    private static HashSet<Character> hs_delimitors;
    private static ArrayList<String> hs_stopwords;

    private Document doc;
    public ArrayList<String> d_wordsCount;
    private String[] l_singleWords;
    private int index;
    public static Parse getInstance() {
        return ourInstance;
    }

    //The class's constructor
    private Parse() {
      //  this.l_singleWords = new ArrayList<String>();//A data structure that contains the initial words
        this.d_wordsCount = new ArrayList<String>();// data structure that contains the final words after all the required tests
        try {
            Scanner s = new Scanner(new File("C:\\Users\\pc\\Desktop\\corpus\\stopWords.txt"));
            hs_stopwords = new ArrayList<String>();
            while (s.hasNext()) {
                hs_stopwords.add(s.next());
            }
            s.close();
        } catch (IOException e) {
            // Handle a potential exception
        }

    }

    // Parse the document according to the set of rules given to us in the assignment
    //public void parseDoc(Document doc)
    public ArrayList<String> parseDoc(String articleKey,String txt) {
        l_singleWords = txt.split("\\s+");
        index = 0;
        int[] results = new int[2];
        boolean ifFound;
        String[] rulesToCheack = {"DayMonthRule", "MothYearRule","ExpressionsRepresentationRule", "SingleWordRule","PhoneNumberRepresentationRule", "PriceRepresentationRule", "PrecentageRepresentationRole", "RangeRule","KiloOrMeterRepresentationRule", "NumberRepresentationRule"};
        while (index < l_singleWords.length) {

            if (l_singleWords[index].equals("")) {
                index++;
                continue;
            }
            if(l_singleWords[index].startsWith("<")){
                String str = l_singleWords[index].replaceAll("\\<.*?\\>", "");
                l_singleWords[index] = str;

            }
            ifFound = false;
            results[0] = 0;
            results[1] = 0;
            for (int i = 0; i < rulesToCheack.length&&index<l_singleWords.length; i++) {
                if (l_singleWords[index].equals("")) {
                    break;
                }
                IRuleChecker Rulechecker = RulesFactory.getRuleChecker(rulesToCheack[i]);
                results = Rulechecker.roleChecker(l_singleWords, articleKey, index);
                if (results[0] == 1) {
                    index += results[1];
                    ifFound = true;
                    break;
                }
            }
            if (ifFound)
                continue;
            else {
                CorpusDictenory dictenory = CorpusDictenory.getInstance();
                index++;
            }
        }
        return d_wordsCount;
    }

    public boolean checkStopWord(String checkWord) {
        checkWord = deleteDelimitors(l_singleWords[index]);
        checkWord = checkWord.toLowerCase(); // problem with May and may
        if (hs_stopwords.contains(checkWord)) {
            return true;
        }
        return false;
    }

    private String deleteDelimitors(String word) {
        if (word.equals("U.S.")) {
            return word;
        }
        if (word.startsWith("<")||word.endsWith(">")) {
            word = word.replaceAll("\\<.*?\\>", "");
            word=word.replaceAll("<","");
            word=word.replaceAll(">","");
        }
        if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")")  || word.endsWith(";") || word.endsWith(":") || word.endsWith("]")||word.endsWith("\"")) {
            word = word.substring(0, word.length() - 1);
            if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")")  || word.endsWith(";") || word.endsWith(":") || word.endsWith("]")||word.endsWith("\""))
                word = word.substring(0, word.length() - 1);
        }
        if (word.startsWith("(") || word.startsWith("\"") || word.startsWith("["))
            word = word.substring(1);
        int wordLength = word.length();
        if (word.startsWith("-")) {
            while (word.startsWith("-") && !word.isEmpty()) {
                word = word.substring(1);
            }
        }

        return word;
    }
}





