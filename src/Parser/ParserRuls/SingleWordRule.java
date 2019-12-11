package Parser.ParserRuls;


import Parser.Parse;

import java.util.ArrayList;
/// This checks if a word given to us qualifies as a single word without any special format, e.g. : "study".
public class SingleWordRule extends AWordsRule{
    public int[] roleChecker(String[] words, String key,int index){
        results[0] = 0;
        results[1] = 0;
        String word = getWord(words,index);
        if(((word == null)
                || (word.equals(""))
               || ((!word.matches("[a-zA-Z]+\\'[S,s]"))&&
        (!word.matches("[a-zA-Z]+" )))))
            return results;
        Parse parse =Parse.getInstance();
        if(parse.checkStopWord(word)){
            return results;
        }
        results[0] = 1;
        results[1] = 1;
        word = stem(word);
        addToDictionaryWord(word,key);
        return results;
    }

    private String stem(String term){
        for(int i=0;i<term.length();i++){
            if(term.charAt(i)!='\'')
            stemmer.add(term.charAt(i));
        }
        String str = stemmer.stem();
        stemmer.clear();
        return str;

    }
}
