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

    private static Stemmer stemmer;
    private static ArrayList<String> d_months;
    private static HashSet<Character> hs_delimitors;
    private static ArrayList<String> hs_stopwords;

    private Document doc;

    //change to hashset
    public ArrayList<String> d_wordsCount;
    private String[] l_singleWords;

  //  public ArrayList<String> l_singleWords;
    private int index;


    //The class's constructor
    public Parse(String txt) {
      //  this.l_singleWords = new ArrayList<String>();//A data structure that contains the initial words
        this.d_wordsCount = new ArrayList<String>();//A data structure that contains the final words after all the required tests
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
        l_singleWords = txt.split("( )|(\\t)");
    }

    // Parse the document according to the set of rules given to us in the assignment
    //public void parseDoc(Document doc)
    public ArrayList<String> parseDoc(String articleKey) {
        index = 0;
        int[] results = new int[2];
        boolean ifFound;
        String[] rulesToCheack = {"DayMonthRule", "MothYearRule", "SingleWordRule", "PriceRepresentationRule", "PrecentageRepresentationRole", "RangeRule", "NumberRepresentationRule"};
        while (index < l_singleWords.length) {
            if (checkStopWord()||l_singleWords[index].startsWith("<")){
                index++;
                continue;
            }


            if (l_singleWords[index].equals("")) {
                index++;
                continue;
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
              //  dictenory.addWord(l_singleWords[index], articleKey);
                index++;
            }
        }
        return d_wordsCount;
    }

    /// delete the delimitors from the start and the end of the string in the list


    // This function checks if the next word we are parsing is a stopword, if it is there's no point going over it.
    // <returns>true whether the next word is a stopword, false otherwise</returns>
    private boolean checkStopWord() {
        String checkWord = deleteDelimitors(l_singleWords[index]);
        checkWord = checkWord.toLowerCase(); // problem with May and may
        if (hs_stopwords.contains(checkWord)) {
            index = index + 1;
            return true;
        }
        return false;
    }

    private String deleteDelimitors(String word) {
        if (word.equals("U.S.")) {
            return word;
        }
        if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")") || word.endsWith("(") || word.endsWith(";") || word.endsWith(":") || word.endsWith("]")) {
            word = word.substring(0, word.length() - 1);
            if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")") || word.endsWith("(") || word.endsWith(";") || word.endsWith(":") || word.endsWith("]"))
                word = word.substring(0, word.length() - 1);
        }
        if (word.startsWith("(") || word.startsWith(")") || word.startsWith("["))
            word = word.substring(1);
        int wordLength = word.length();
        if (word.startsWith("-")) {
            while (word.startsWith("-") && !word.isEmpty()) {
                word = word.substring(1);
            }
        }
        return word;
    }
/*
    //(703) 733-6097
    //703 733-6097
    private boolean phoneNumberRepresentationRule() {
        String checkWord = l_singleWords.get(index);
        boolean isNumber = isNumber(checkWord);
        if (isNumber && checkWord.length() == 3) {
            String checkWord2 = l_singleWords.get(index + 1);
            boolean isNumber2 = isNumber(checkWord2.substring(1, 3));
            boolean isNumber3 = isNumber(checkWord2.substring(5));
            if (isNumber2 && isNumber3 && checkWord2.charAt(4) == '-') {
                index += 2;
                return true;
            }
        }

        return false;
    }

    //
    private boolean kiloOrMeterRepresentationRule() {
        String checkWord = l_singleWords.get(index);
        boolean isNumber = isNumber(checkWord);
        String nextWord = l_singleWords.get(index + 1);
        if ((isNumber) && (nextWord.equalsIgnoreCase("kilo") || nextWord.equalsIgnoreCase("kilogram") || nextWord.equalsIgnoreCase("meters"))) {
            index += 2;
            return true;
        }

        return false;

    }

    //Shachar The King
    private boolean expressionsRepresentationRule() {
        String checkWord = l_singleWords.get(index);
        if (checkWord.charAt(0) >= 'A' && checkWord.charAt(0) <= 'Z') {
            String checkWord2 = l_singleWords.get(index + 1);
            if (checkWord2.charAt(0) >= 'A' && checkWord2.charAt(0) <= 'Z') {
                String checkWord3 = l_singleWords.get(index + 2);
                if (checkWord2.charAt(0) >= 'A' && checkWord2.charAt(0) <= 'Z') {
                    index += 3;
                    return true;
                } else {
                    index += 2;
                    return true;
                }

            }
            return false;
        }
        return false;

    }
*/
}






