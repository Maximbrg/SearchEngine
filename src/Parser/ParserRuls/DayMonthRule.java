package Parser.ParserRuls;

import java.util.ArrayList;


public class DayMonthRule extends ADatesRule {


    public int[] roleChecker(ArrayList<String> words,ArrayList<String> d_wordsCount) {
        //To qualify as a month-day format there needs to be atleast 2 strings.


        if (words.get(0).contains("%")) {
            return results;
        }
        //We will look at the two next words in their lowercase form.
        String day = words.get(0).toLowerCase();
        String month = words.get(1).toLowerCase();
        //check if the first is the day
        if (!isNumeric(day)) {
            String temp = day;
            day = month;
            month = temp;

        }

        //check if the second word can be the day.
        if(!isNumeric(day)){
            return results;

        }
        int intDay = Integer.parseInt(day);
        int intMonth=-1;
        if (intDay > 0 && intDay <= 31 && d_months.contains(month))
        {
            String result;
            String result2;

            intMonth=changeMonthToNumber(month);

            if (intMonth < 10)
                result2 = "0" + intMonth;
            else
                result2 = intMonth + "";
            if (intDay < 10)
                result = result2 + "-0" + intDay;
            else
                result = result2 +"-" + intDay;
            addToDictionary(result,d_wordsCount);
            results[0] = 1;
            results[1] = 1;
            return results;
        }
        return results;



    }

    private void addToDictionary(String toAdd,ArrayList<String> d_wordsCount)
    {
        if ((d_wordsCount.contains(toAdd))==false){
            d_wordsCount.add(toAdd);
        }
    }
}
