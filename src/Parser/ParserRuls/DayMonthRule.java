package Parser.ParserRuls;

import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.isNumber;


public class DayMonthRule extends ADatesRule {


    public int[] roleChecker(String[] words,String key,int index) {
        //To qualify as a month-day format there needs to be atleast 2 strings.
        results[0] = 0;
        results[1] = 0;
        if(words[index].length()==0)
            return results;
        if (getWord(words,index).endsWith("%")/*|| getWord(words,index).endsWith(".") || getWord(words,index).endsWith(",")*/) {
            return results;
        }
        //We will look at the two next words in their lowercase form.
        if (words.length < index + 2)
            return results;
        String day =getWord(words,index).toLowerCase();
        String month = getWord(words,index+1).toLowerCase();
        if(!isNumber(day)&&!isNumber((month)))
            return results;
        if(day.equals("")||month.equals(""))
            return results;
        //   if(!isNumber(day)&&!isNumber(day))
        //   return results;
        //check if the first is the day
        if (!isNumeric(day)) {
            String temp = day;
            day = month;
            month = temp;

        }
        //check if the second word can be the day.
            int intDay = Integer.parseInt(day);
            int intMonth = -1;
            if (intDay > 0 && intDay <= 31 && d_months.contains(month)) {
                String result;
                String result2;

                intMonth = changeMonthToNumber(month);

                if (intMonth < 10)
                    result2 = "0" + intMonth;
                else
                    result2 = intMonth + "";
                if (intDay < 10)
                    result = result2 + "-0" + intDay;
                else
                    result = result2 + "-" + intDay;
                addToDictionary(result, key);
                results[0] = 1;
                results[1] = 2;
                return results;
            }

        return results;
    }


    }

