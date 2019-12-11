package Parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class CorpusDictenory {
    private static CorpusDictenory ourInstance = new CorpusDictenory();
    public TreeMap<String, LinkedList<String>> Dictenory;
    int counter = 0;
    public HashMap<String, String[]> ArticleInform;

    public static CorpusDictenory getInstance() {
        return ourInstance;
    }

    private CorpusDictenory() {
        Dictenory = new TreeMap<>();
        ArticleInform = new HashMap<>();
    }

    public void addNumber(String word, String articleKey) {
        if (word.endsWith("."))
            word = word.substring(0, word.length() - 1);
        if (Dictenory.containsKey(word)) {
            LinkedList<String> tmp = Dictenory.get(word);
            tmp.add(articleKey);
            Dictenory.replace(word, Dictenory.get(word), tmp);
            addInfoToArticleInfo(word, articleKey);
            counter++;
        } else {
            LinkedList<String> tmp = new LinkedList<>();
            tmp.add(articleKey);
            Dictenory.put(word, tmp);
            addInfoToArticleInfo(word, articleKey);
            counter++;
        }


    }

    public void addWord(String word, String articleKey) {
        if (word.endsWith("."))
            word = word.substring(0, word.length() - 1);
        String tempWord = "";
        if (word.length() < 1)
            return;
        if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
            word = word.toUpperCase();
            tempWord = word.toLowerCase();
        } else {
            word = word.toLowerCase();
            tempWord = word.toUpperCase();
        }

        if (Dictenory.containsKey(word)) {
            LinkedList<String> tmp = Dictenory.get(word);
            tmp.add(articleKey);
            Dictenory.replace(word, Dictenory.get(word), tmp);
            addInfoToArticleInfo(word, articleKey);
            counter++;
        } else {
            if (Dictenory.containsKey(tempWord)) {
                if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
                    LinkedList<String> tmp = Dictenory.get(tempWord);
                    tmp.add(articleKey);
                    Dictenory.replace(tempWord, Dictenory.get(tempWord), tmp);
                    addInfoToArticleInfo(word, articleKey);
                    counter++;
                } else {
                    LinkedList<String> tmp = Dictenory.get(tempWord);
                    tmp.add(articleKey);
                    Dictenory.remove(tempWord);
                    Dictenory.put(word, tmp);
                    addInfoToArticleInfo(word, articleKey);
                    counter++;
                }
            } else {
                LinkedList<String> tmp = new LinkedList<>();
                tmp.add(articleKey);
                Dictenory.put(word, tmp);
                addInfoToArticleInfo(word, articleKey);
                counter++;
            }
        }
    }

    private void addInfoToArticleInfo(String word, String articleKey) {
        if (word.endsWith("."))
            word = word.substring(0, word.length() - 1);
        if (!ArticleInform.containsKey(articleKey)) {
            String[] information = {"0", "", "0"};
            ArticleInform.put(articleKey, information);
        }
        try {
            List<String> termInfo = Dictenory.get(word);
            if (termInfo == null)
                termInfo = Dictenory.get(word.toUpperCase());
            if (termInfo == null)
                termInfo = Dictenory.get(word.toLowerCase());
            int numOfOcurance = Collections.frequency(termInfo, articleKey);
            String[] temp = ArticleInform.get(articleKey);
            if (numOfOcurance == 1) {
                temp[0] = (Integer.parseInt(temp[0]) + 1) + "";
            }
            if (Integer.parseInt(temp[2]) < numOfOcurance) {
                temp[2] = numOfOcurance + "";
                temp[1] = word;
            }
            ArticleInform.replace(articleKey, ArticleInform.get(articleKey), temp);

        } catch (Exception e) {

        }

    }


    public void printsize() {
        System.out.println(Dictenory.size());
    }

    public TreeMap<String, LinkedList<String>> getDictenory() {
        TreeMap<String, LinkedList<String>> newDic = new TreeMap<>();
        for (String name : Dictenory.keySet()) {
            LinkedList<String> tmp = Dictenory.get(name);
            LinkedList<String> newList = new LinkedList<>();
            boolean flag = false;
            int counter = 0;
            String curr = "";
            for (String str : tmp) {
                if (str.equals(curr)) {
                    counter++;
                    //tmp.remove(str);
                } else {
                    if (counter != 0)
                        newList.add(curr + "," + counter);
                    curr = str;
                    flag = true;
                    counter = 1;

                }
            }
            if (flag) {
                if (counter != 0)
                    newList.add(curr + "," + counter);
            }
            newDic.put(name, newList);
        }
        return newDic;
    }

    public HashMap<String, String[]> getArticlea() {
        Object temp = ArticleInform.clone();
        ArticleInform.clear();
        return (HashMap) temp;
    }

    public TreeMap<String, LinkedList<String>> getDicteneryaaa() {

        Object temp = Dictenory.clone();
        Dictenory.clear();
        return (TreeMap) temp;
    }
}