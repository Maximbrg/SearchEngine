package Parser.ParserRuls;

import Parser.CorpusDictenory;

import java.util.ArrayList;

public abstract  class ARuleChecker implements IRuleChecker {
    protected void addToDictionary(String toAdd, String key) {
        CorpusDictenory dictenory = CorpusDictenory.getInstance();
        dictenory.addNumber(toAdd, key);
    }

    protected void addToDictionaryWord(String toAdd, String key) {
        CorpusDictenory dictenory = CorpusDictenory.getInstance();
        dictenory.addWord(toAdd, key);
    }

    protected String getWord(String[] words, int i) {
        String word = words[i];
        word = deleteDelimitors(word);
        words[i] = word;
        return word;

    }

    private String deleteDelimitors(String word) {

            if (word.startsWith("<")||word.endsWith(">")) {
                word = word.replaceAll("\\<.*?\\>", "");
                word=word.replaceAll("<","");
                word=word.replaceAll(">","");
            }
            if (word.equals("U.S.")) {
                return word;
            }
        if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")")  || word.endsWith(";") || word.endsWith(":") || word.endsWith("]")||word.endsWith("\"")||word.endsWith("?")) {
            word = word.substring(0, word.length() - 1);
            if (word.endsWith(",") || word.endsWith(".") || word.endsWith(")")  || word.endsWith(";") || word.endsWith(":") || word.endsWith("]")||word.endsWith("\""))
                word = word.substring(0, word.length() - 1);
        }
         if (word.startsWith("(") || word.startsWith("\"") || word.startsWith("[")|| word.startsWith("?"))
                word = word.substring(1);
            int wordLength = word.length();
            if (word.startsWith("-") || word.startsWith(".")) {
                while ((word.startsWith("-") || word.startsWith(".")) && !word.isEmpty()) {
                    word = word.substring(1);
                }
            }
        return word;
    }
}
