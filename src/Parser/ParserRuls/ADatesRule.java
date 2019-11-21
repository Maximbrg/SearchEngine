package Parser.ParserRuls;

import java.util.ArrayList;
import java.util.Arrays;

public abstract  class ADatesRule implements IRuleChecker {


    protected static ArrayList<String> d_months;
    protected  int[] results = new int[2];


    public ADatesRule(){
        d_months= new ArrayList<String >( Arrays.asList("january","february","march","april","may","june","july","august",

                "september","october","november","december","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"));
        results[0] = 0;
        results[1] = 0;
    }


    protected static int changeMonthToNumber(String str) {
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

    //This boolean func check if str is number
    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
