package Parser.ParserRuls;

import java.util.ArrayList;

public class MonthYearRule extends ADatesRule {
    //This function checks the format of YYYY-MM.
    public int[] roleChecker(ArrayList<String> words, ArrayList<String> d_wordsCount)
    {
        //To qualify as a month-year format there needs to be atleast 2 strings.
  //      if(index + 1 > l_singleWords.size()){
    //        return false;
      //  }


        if(words.get(0).contains("%")){
            return results;
        }
        //We will look at the two next words in their lowercase form.
        String month = words.get(0).toLowerCase();
        String year =words.get(1).toLowerCase();
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
