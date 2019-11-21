package Parser.ParserRuls;

import java.util.ArrayList;

public class PrecentageRepresentationRole extends ANumberRules {

    public int[] roleChecker(ArrayList<String> words, ArrayList<String> d_wordsCount){
        String str = words.get(0);
        if(str.charAt(str.length()-1)== '%'&& str.length()>1 && isNumber(str.substring(0,str.length()-1)))
        {
            addToDictionary(str,d_wordsCount);
            results[0]=1;
            results[1]=1;
            return results;
        }
        else if (isNumber(str) && /*index < l_singleWords.size() - 1 &&*/words.get(1).equals("percent")|| words.get(1).equals("percentage"))
        {
            addToDictionary(str + '%',d_wordsCount);
            results[0]=1;
            results[1]=2;
            return results;
        }
        return results;
    }

    private void addToDictionary(String toAdd,ArrayList<String> d_wordsCount)
    {
        if ((d_wordsCount.contains(toAdd))==false){
            d_wordsCount.add(toAdd);
        }
    }
}
