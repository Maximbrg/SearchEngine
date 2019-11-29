package Parser.ParserRuls;

import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.isNumber;


public class DayMonthRule extends ADatesRule {


    public int[] roleChecker(ArrayList<String> words,String key) {
        //To qualify as a month-day format there needs to be atleast 2 strings.
        results[0]=0;
        results[1]=0;
        if (words.get(0).contains("%")||words.get(0).contains(".")||words.get(0).contains(",")) {
            return results;
        }
        //We will look at the two next words in their lowercase form.
        if(words.size()<2)
            return results;
        String day = words.get(0).toLowerCase();
        String month = words.get(1).toLowerCase();
        if((!isNumber(day)&&!isNumber(day))||day.length()>3||month.length()>3)
            return results;
        //check if the first is the day
        if (!isNumeric(day)) {
            String temp = day;
            day = month;
            month = temp;

        }
        //check if the second word can be the day.
            if (!isNumeric(day)) {
                return results;

            }
        float intDay = Integer.parseInt(day);
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
            addToDictionary(result,key);
            results[0] = 1;
            results[1] = 1;
            return results;
        }
        return results;



    }

}
