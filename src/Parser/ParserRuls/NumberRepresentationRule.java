package Parser.ParserRuls;

import java.util.ArrayList;

public class NumberRepresentationRule extends ANumberRules{

    public int[] roleChecker(ArrayList<String> words, String key,int index){
        /*
        results[0]=0;
        results[1]=0;
        String word = words.get(0);
        if (!isNumber(word))
            return results;
        if(word.contains(",")) {
            for (int k = 0; k < word.length(); k++) {
                if (!(word.charAt(k) == ',')) {
                    word = word + word.charAt(k);
                }
            }
        }
            double number = Double.parseDouble(word);
        */
        results[0]=0;
        results[1]=0;
    String word = words.get(index);
        if (!isNumber(word))
            return results;
    String nextWord = "";
    double number = 0.0;
    String tempWord="";
        if(word.contains(",")){
        for (int k = 0;  k<word.length() ; k++){
            if(!(word.charAt(k)==',')){
                tempWord= tempWord +word.charAt(k);
            }

        }
            number = Double.parseDouble(tempWord);
    }
       else {
                number = Double.parseDouble(word);
  }
        if (index < words.size() - 1)
    nextWord = words.get(index+1);

    //check if the next word incrice our number
        if (nextWord.equals("thousand") || nextWord.equals("Thousand"))
    {
        number = number * 1000;
        results[0]=1;
        results[1]=1;
    }
        else if (nextWord.equals("Million") || nextWord.equals("million"))
    {
        number = number * 1000000;
        results[0]=1;
        results[1]=1;
    }
        else if (nextWord.equals("Billion") || nextWord.equals("billion"))
    {
        number = number * 1000000000;
        results[0]=1;
        results[1]=1;
    }

    //check how many digits left to the poiont we have and update the nMultiper according to it
    long longNumber;
    longNumber = (long)number;
    int nMultiplier = 0; //how much we need to incrice the number
    int numOfOcc = String.valueOf(longNumber).length();

        if (numOfOcc <=3)
    {
        number = roundTheDigits(number);
        number = roundTheDigits(number);
        addToDictionary(String.valueOf(number),key);
        results[0]=1;
        results[1]++;
        return results;
    }
        if (numOfOcc >3 &&numOfOcc <=6)
    {
        number = number / 1000;
        number = roundTheDigits(number);
        addToDictionary(String.valueOf(number) + 'K',key);
        results[0]=1;
        results[1]++;
        return results;
    }
        else if (numOfOcc >6 &&numOfOcc <=9)
    {
        number = number / 1000000;
        number = roundTheDigits(number);
        addToDictionary(String.valueOf(number) + 'M',key);
        results[0]=1;
        results[1]++;
        return results;
    }
        else if (numOfOcc >= 10)
    {
        number = number / 1000000000;
        number = roundTheDigits(number);
        addToDictionary(String.valueOf(number) + 'B',key);
        results[0]=1;
        results[1]++;
        return results;
    }
        results[0]=1;
        results[1]++;
        return results;

}
    private double roundTheDigits(double toRound)
    {
        int help;
        help = (int)(toRound * 100);
        toRound = (double)help / 100;
        return toRound;
    }

}
