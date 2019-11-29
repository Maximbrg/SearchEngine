package Parser.ParserRuls;

import Parser.CorpusDictenory;

import java.util.ArrayList;

public abstract  class ARuleChecker implements IRuleChecker {
    protected void addToDictionary(String toAdd, String key)
    {
        CorpusDictenory dictenory= CorpusDictenory.getInstance();
        dictenory.addWord(toAdd,key);
    }

}
