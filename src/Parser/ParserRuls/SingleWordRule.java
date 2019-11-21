package Parser.ParserRuls;


import java.util.ArrayList;
/// This checks if a word given to us qualifies as a single word without any special format, e.g. : "study".
public class SingleWordRule extends AWordsRoule{
    public int[] roleChecker(ArrayList<String> words, ArrayList<String> d_wordsCount){
        String word = words.get(0);
        for (int i=0; i<word.length(); i++) {
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z' || word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
                return results;
        }

        word = stemmer.stem();
        if(word.charAt(0) <= 'a' || word.charAt(0) >= 'z' ) {

            String tempWord = word.toUpperCase();
            if (d_wordsCount.contains(tempWord)) {
                d_wordsCount.remove(tempWord);
                d_wordsCount.add(word);

            } else {
                tempWord.toUpperCase();
                d_wordsCount.add(tempWord);
            }
            results[0]=1;
            results[1]=1;
            return results;
        }
        if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
            String tempWord = word.toLowerCase();
            if (d_wordsCount.contains(tempWord)) {
                results[0]=1;
                results[1]=1;
                return results;
            } else {
                word.toUpperCase();
                d_wordsCount.add(word);
                results[0]=1;
                results[1]=1;
                return results;
            }
        }
        return results;
    }
}
