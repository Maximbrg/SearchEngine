package ReadFile;

import javax.swing.text.Document;
import java.util.*;
import java.lang.String;



public class Parse {


    private static Stemmer stemmer;
    private static ArrayList<String> d_months;
    private static HashSet<Character> hs_delimitors;
    private static HashSet<String> hs_stopwords;

    private Document doc;

    //change to hashset
    public ArrayList<String> d_wordsCount;

    public ArrayList<String> l_singleWords;
    private int index;




    //The class's constructor
    public Parse(String pathStopwords)
    {

        this.l_singleWords = new ArrayList<String>();//A data structure that contains the initial words
        this.d_wordsCount = new ArrayList<String>();//A data structure that contains the final words after all the required tests
        char[] tempChar= new char[] { ' ', '\t', '\n' };
        String [] textArray = pathStopwords.split(" ");
        for(int i=0;i<textArray.length;i++){
            l_singleWords.add(textArray[i]);
        }

        if(stemmer==null){// This is the first iteration
            stemmer = new Stemmer();
            hs_delimitors = new HashSet<Character>(Arrays.asList('\'','(','[','{',')',']','}',',','.',';','/','\\','-', // "-"
                    '#','!','?','*',':','`','|', '&','^','*','@', '"'));
            d_months= new ArrayList<String >( Arrays.asList("january","february","march","april","may","june","july","august",
                    "september","october","november","december","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"));


            /*
            try {
                hs_stopwords = new HashSet<String>(Files.readAllLines(Paths.get(pathStopwords)));
            }
            catch (IOException e) {
                // Handle a potential exception
            }

             */
        }


    }


    // Parse the document according to the set of rules given to us in the assignment
    //public void parseDoc(Document doc)
    public ArrayList<String> parseDoc()
    {
        index = 0;
        //this.doc = doc;


        //we need to take one word from doc



        //stringSplitOptions.RemoveEmptyEntries



/*
        //deleteDelimitors
        this.l_singleWords.add("hello,");
        this.l_singleWords.add("shachar!");
        this.l_singleWords.add("/10");
        //checkRange
        this.l_singleWords.add("word-");
        this.l_singleWords.add("word");
        this.l_singleWords.add("10-");
        this.l_singleWords.add("10");
        this.l_singleWords.add("between");
        this.l_singleWords.add("10");
        this.l_singleWords.add("and");
        this.l_singleWords.add("20");


        //checks MM-DD.
        this.l_singleWords.add("14");
        this.l_singleWords.add("May");
        this.l_singleWords.add("June");
        this.l_singleWords.add("4");


        //checks YYYY-MM.
        this.l_singleWords.add("May");
        this.l_singleWords.add("1994");


        //checks precentageRepresentation.
        this.l_singleWords.add("6%");
        this.l_singleWords.add("10.6");
        this.l_singleWords.add("percent");
        this.l_singleWords.add("10.6");
        this.l_singleWords.add("percentage");
*/
       // deleteDelimitors();//The function removes all punctuation marks from all words in the repository before we start working with them.


        while (index < l_singleWords.size())
        {
            if (l_singleWords.get(index) == "")
            {
                index++;
                continue;
            }
            else if (checkRange())
                continue;
            else if (checkDayMonth())
                continue;
            else if (checkMonthYear())
                continue;
         //   else if (checkStopWord())
             //   continue;
            else if (precentageRepresentation())
                continue;
            else
                index++;
        }

/*
        doc.d_wordsCount = this.d_wordsCount;
        this.d_wordsCount = new Dictionary<string, int>();
*/

return d_wordsCount;

    }

    /// delete the delimitors from the start and the end of the string in the list
    private void deleteDelimitors()
    {
        for (int i = 0; i < l_singleWords.size(); i++)
        {
            int start=0;
            int end=0;
            //for every word in l_singleWords from start
            for (start = 0; start < l_singleWords.get(i).length(); start++)
            {

                if (!hs_delimitors.contains(l_singleWords.get(i).charAt(start)))
                    break;
                if (l_singleWords.get(i).charAt(start) == '-')
                    if (start + 1 < l_singleWords.get(i).length() && l_singleWords.get(i).charAt(start+1) <= 9 && l_singleWords.get(i).charAt(start+1) >= 0)
                        break;
            }
            //from end of the word
            for (end = l_singleWords.get(i).length() - 1; end > start; end--)
            {
                if (!hs_delimitors.contains(l_singleWords.get(i).charAt(end)))
                    break;
            }
            //take the new word without the delimitors.
            String temp =  l_singleWords.get(i).substring(start, end + 1 - start);
            l_singleWords.set(i,temp) ;
        }
    }

    /////////////////
    // add string to d_wordsCount
    ////////////maybe need to save the dictionary in hashset
    ////////if it be hash set we dont need to check if contain
    private void addToDictionary(String toAdd)
    {
        if ((d_wordsCount.contains(toAdd))==false){
            d_wordsCount.add(toAdd);
        }
    }

    /// This function will be encharge of checking whether the word we're currently inspecting is a range indicator ('3-4' for example)
    private boolean checkRange()
    {
        //We will now examine the word
        String word = l_singleWords.get(index).toLowerCase();
        String result = "";
        //If the word is between, just like in the stopwords check, and there is a format of a range following it, we need to add it here.
        if (word == "between" && index + 3 < l_singleWords.size())
        {
            if(isNumeric(l_singleWords.get(index + 1))&&isNumeric(l_singleWords.get(index + 3))){

                if(l_singleWords.get(index+2).toLowerCase().equals("and")){

                    double num1 = Double.parseDouble(l_singleWords.get(index + 1));
                    double num3 = Double.parseDouble(l_singleWords.get(index + 3));
                    result = num1 + "-" + num3;
                    index += 4;


                }

            }

        }

        else if (word.indexOf('-') > 0)
        {

            result=result+word;
            while(word.indexOf('-') > 0){
                index++;
                word=l_singleWords.get(index);
                result=result+word;
            }
            index++;

        }
        if (result.length() == 0)
            return false;
        addToDictionary(result);
        return true;
    }

    //This boolean func check if str is number
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    private static int changeMonthToNumber(String str) {
        if(str.equals("january")||str.equals("jan")){
            return 1;
        }
        else if(str.equals("february")||str.equals("feb")){
            return 2;
        }
        else if(str.equals("march")||str.equals("mar")){
            return 3;
        }
        else if(str.equals("april")||str.equals("apr")){
            return 4;
        }
        else if(str.equals("may")){
            return 5;
        }
        else if (str.equals("june")||str.equals("jun")){
            return 6;
        }
        else if(str.equals("july")||str.equals("jul")){
            return 7;
        }
        else if(str.equals("august")||str.equals("aug")){
            return 8;
        }
        else if(str.equals("september")||str.equals("sep")){
            return 9;
        }
        else if(str.equals("october")||str.equals("oct")){
            return 10;
        }
        else if(str.equals("november")||str.equals("nov")){
            return 11;
        }
        else if(str.equals("december")||str.equals("dec")){
            return 12;
        }
        return -1;

    }


    // This function checks the format of MM-DD.
    private boolean checkDayMonth()
    {
        //To qualify as a month-day format there needs to be atleast 2 strings.
        if(index + 1 > l_singleWords.size()){
            return false;
        }
        if(l_singleWords.get(index).contains("%")){
            return false;
        }
        //We will look at the two next words in their lowercase form.
        String day = l_singleWords.get(index).toLowerCase();
        String month = l_singleWords.get(index+1).toLowerCase();
        //check if the first is the day
        if(isNumeric(day)==false){
            String temp = day;
            day = month;
            month = temp;

        }

        //check if the second word can be the day.

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
            addToDictionary(result);
            index=index+2;
            return true;
        }
        return false;


    }

    //This function checks the format of YYYY-MM.

    private boolean checkMonthYear()
    {
        //To qualify as a month-year format there needs to be atleast 2 strings.
        if(index + 1 > l_singleWords.size()){
            return false;
        }
        if(l_singleWords.get(index).contains("%")){
            return false;
        }
        //We will look at the two next words in their lowercase form.
        String month = l_singleWords.get(index).toLowerCase();
        String year =l_singleWords.get(index+1).toLowerCase();
        //check if the year slot is a number as it should be in the format
        int intYear = Integer.parseInt(year);
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
            addToDictionary(result);
            index += 2;
            return true;
        }

        return false;
    }

    // This function checks if the next word we are parsing is a stopword, if it is there's no point going over it.
    // <returns>true whether the next word is a stopword, false otherwise</returns>
    private boolean checkStopWord()
    {
        String checkWord = l_singleWords.get(index).toLowerCase();
        if (hs_stopwords.contains(checkWord))
        {
            index++;
            return true;
        }
        return false;
    }

    // check if the string is a valid representation of number
    private boolean isNumber(String sNumber)
    {
        if (sNumber.charAt(0) != '-' && (sNumber.charAt(0) < '0' || sNumber.charAt(0) > '9'))
            return false;
        for (int i = 1; i < sNumber.length(); i++)
        {

            if ((sNumber.charAt(i) < '0' || sNumber.charAt(i) > '9') && sNumber.charAt(i) != '.' && sNumber.charAt(i) != ',')
                return false;
        }
        return true;
    }
    // Checks if its precent function and add it to d_wordsCount if it's fit and promote the index
    private boolean precentageRepresentation()
    {
        String str = l_singleWords.get(index);
        if(str.charAt(str.length()-1)== '%'&& str.length()>1 && isNumber(str.substring(0,str.length()-1)))
        {
            addToDictionary(str);
            index++;
            return true;
        }
        else if (isNumber(str) && index < l_singleWords.size() - 1 &&l_singleWords.get(index+1).equals("percent")|| l_singleWords.get(index+1).equals("percentage"))
        {
            addToDictionary(str + '%');
            index=index+2;
            return true;
        }
        return false;

    }






}
