package Parser.ParserRuls;

import java.util.ArrayList;

public class MonthYearRule extends ADatesRule {
    //This function checks the format of YYYY-MM.
    public int[] roleChecker(ArrayList<String> words, String key,int index)
    {
        results[0]=0;
        results[1]=0;
        if(words.get(index).contains("%")){
            return results;
        }
        //We will look at the two next words in their lowercase form.
        if(words.size()<2+index)
            return results;
        String month = words.get(index).toLowerCase();
        String year =words.get(index+1).toLowerCase();
        //check if the year slot is a number as it should be in the format
        int intYear;
        try {
            intYear = Integer.parseInt(year);
        }
        catch(Exception e) {
            return results;
        }

        int intMonth=-1;
        String result2;
        if (intYear > 0 && d_months.contains(month))
        {
            intMonth=changeMonthToNumber(month);
            if (intMonth < 10)
                result2 = "0" + intMonth;
            else
                result2 = intMonth + "";
            String result = year + "-" + result2;
            addToDictionary(result,key);
            results[0] = 1;
            results[1] = 2;
            return results;
        }

        return results;
    }

}
