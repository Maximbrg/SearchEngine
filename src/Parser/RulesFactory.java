package Parser;

import Parser.ParserRuls.*;

public class RulesFactory {

    protected static IRuleChecker getRuleChecker(String RuleName){
        if(RuleName.equals("DayMonthRule"))
            return new DayMonthRule();
        else if(RuleName.equals("MothYearRule"))
            return new MonthYearRule();
        else if(RuleName.equals("NumberRepresentationRule"))
            return new NumberRepresentationRule();
        else if(RuleName.equals("PrecentageRepresentationRole"))
            return new PrecentageRepresentationRole();
        else if(RuleName.equals("RangeRule"))
            return new RangeRule();
        else if(RuleName.equals("SingleWordRule"))
            return new SingleWordRule();
        else if(RuleName.equals("PriceRepresentationRule"))
            return new PriceRepresentationRule();
        return null;
    }
}
