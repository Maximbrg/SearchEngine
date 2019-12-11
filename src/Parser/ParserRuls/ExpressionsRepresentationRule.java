package Parser.ParserRuls;

public class ExpressionsRepresentationRule extends AWordsRule {
   public int[] roleChecker(String[] words, String key,int index){
       results[0]=0;
       results[1]=0;
       String checkWord =getWord(words,index);
       if (!checkWord.equals("")&&index+1<words.length&&checkWord.charAt(0) >= 'A' && checkWord.charAt(0) <= 'Z'&&!checkWord.endsWith(".")) {
           String checkWord2 = getWord(words,index+1);
           if (!checkWord2.equals("")&& index+2<words.length && checkWord2.charAt(0) >= 'A' && checkWord2.charAt(0) <= 'Z'&&!checkWord2.endsWith(".")) {
               String checkWord3 = getWord(words, index + 2);

                   if (!checkWord3.equals("")&&checkWord3.charAt(0) >= 'A' && checkWord3.charAt(0) <= 'Z'&&!checkWord3.endsWith(".")) {
                       results[0] = 1;
                       results[1] = 3;
                       addToDictionary(checkWord + " " + checkWord2 + " " + checkWord3, key);
                   } else {
                       results[0] = 1;
                       results[1] = 2;
                       addToDictionary(checkWord + " " + checkWord2, key);
                   }

               }
               return results;

       }
       return results;
   }

}
