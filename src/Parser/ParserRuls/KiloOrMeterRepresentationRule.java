package Parser.ParserRuls;

public class KiloOrMeterRepresentationRule extends ANumberRules {
    public int[] roleChecker(String[] words, String key,int index) {
        results[0]=0;
        results[1]=0;
        String checkWord = getWord(words,index);
        boolean isNumber = isNumber(checkWord);
        if(!(index+1<words.length))
            return results;
        String nextWord = getWord(words,index+1);
        if ((isNumber) && (nextWord.equalsIgnoreCase("kilo") || nextWord.equalsIgnoreCase("kilogram") || nextWord.equalsIgnoreCase("meters")) || nextWord.equalsIgnoreCase("kg")) {
            results[0]=1;
            results[1]=2;
            addToDictionary(checkWord+nextWord,key);
            return results;
        }

        return results;
    }
}
