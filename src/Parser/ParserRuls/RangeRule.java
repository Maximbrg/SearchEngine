package Parser.ParserRuls;

import java.util.ArrayList;
/// This class will be encharge of checking whether the word we're currently inspecting is a range indicator ('3-4' for example)
public class RangeRule extends ANumberRules {

    public int[] roleChecker(ArrayList<String> words, String key,int index)
    {
        //We will now examine the word
        results[0]=0;
        results[1]=0;
        String word = words.get(index).toLowerCase();
        String result = "";
        //If the word is between, just like in the stopwords check, and there is a format of a range following it, we need to add it here.
       if (word == "between" &&  3 < words.size() )
       {
            if(isNumeric(words.get(index+1))&&isNumeric(words.get(index+3))){

                if(words.get(index+2).toLowerCase().equals("and")){

                    double num1 = Double.parseDouble(words.get(index+1));
                    double num3 = Double.parseDouble(words.get( index+3));
                    result = num1 + "-" + num3;
                   results[0]=1;
                   results[1]=4;

                }
            }
        }
/*
        else if (word.indexOf('-') > 0)
        {

            result=result+word;
            while(word.indexOf('-') > 0){
                index++;
                word=l_singleWords.get(index);
                result=result+word;
            }
            index++;

        }
        */
        if (results[1] == 0)
            return results;
        addToDictionary(result,key);
        return results;
    }

}
