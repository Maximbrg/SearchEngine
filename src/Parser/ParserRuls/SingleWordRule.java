package Parser.ParserRuls;


import java.util.ArrayList;
/// This checks if a word given to us qualifies as a single word without any special format, e.g. : "study".
public class SingleWordRule extends AWordsRule{
    public int[] roleChecker(ArrayList<String> words, String key,int index){
        results[0] = 0;
        results[1] = 0;
        String word = words.get(index);
        for (int i=0; i<word.length(); i++) {
           // if (word.charAt(i) < 'a' || word.charAt(i) > 'z' || word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') // ???
            if (!((word.charAt(i) >= 'a' && word.charAt(i) <= 'z') ||( word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')))
                return results;
        }
        results[0] = 1;
        results[1] = 1;
        word = stem(word);
        addToDictionaryWord(word,key);
        /*
        if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z' ) {

            String tempWord = word.toLowerCase();
            if (d_wordsCount.contains(tempWord)) {
             //   d_wordsCount.remove(tempWord);
                d_wordsCount.add(word);

            } else {
                tempWord.toLowerCase();
                d_wordsCount.add(tempWord);
            }
            results[0]=1;
            results[1]=1;
            return results;
        }
        if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
            String tempWord = word.toUpperCase();
            if (d_wordsCount.contains(tempWord)) {
                d_wordsCount.add(tempWord);
                results[0]=1;
                results[1]=1;
                return results;
            } else {
                d_wordsCount.add(tempWord);
                results[0]=1;
                results[1]=1;
                return results;
            }
        }
        */
        return results;
    }

    private String stem(String term){
        for(int i=0;i<term.length();i++){
            stemmer.add(term.charAt(i));
        }
        String str = stemmer.stem();
        stemmer.clear();
        return str;

    }
}
