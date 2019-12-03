package Parser.ParserRuls;

import java.util.ArrayList;

public class PrecentageRepresentationRole extends ANumberRules {

    public int[] roleChecker(String[] words, String key,int index){
        results[0]=0;
        results[1]=0;
        if(words[index].length()>0)
            return results;
        String str = getWord(words,index);
            if (str.charAt(str.length() - 1) == '%' && str.length() > 1 && isNumber(str.substring(0, str.length() - 1))) {
                addToDictionary(str, key);
                results[0] = 1;
                results[1] = 1;
                return results;
            } else if (isNumber(str) && index < words.length-1  && (getWord(words,index+1).equals("percent") || getWord(words,index+1).equals("percentage")))  {
                addToDictionary(str + '%', key);
                results[0] = 1;
                results[1] = 2;
                return results;
            }
        return results;
    }

}
