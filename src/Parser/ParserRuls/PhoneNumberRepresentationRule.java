package Parser.ParserRuls;

public class PhoneNumberRepresentationRule extends ANumberRules {
    //(703) 733-6097
    //703 733-6097
    public int[] roleChecker(String[] words, String key,int index) {
        results[0] = 0;
        results[1] = 0;
        String checkWord = getWord(words,index);
        boolean isNumber = isNumber(checkWord);
        if (isNumber && checkWord.length() == 3&index+1<words.length) {
            String checkWord2 = getWord(words,index+1);
            String tmp = checkWord+checkWord2;
            if ((tmp.matches("^[a-zA-Z]*[\\-]{1}[a-zA-Z]+\\-[a-zA-Z]+$"))) {
                results[0] = 1;
                results[1] = 2;
                addToDictionary(tmp, key);
                return results;
            }
        }

        return results;
    }
}
