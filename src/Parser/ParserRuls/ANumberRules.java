package Parser.ParserRuls;

public abstract class ANumberRules implements IRuleChecker {

    protected  int[] results = new int[2];

    public ANumberRules(){
        results[0] = 0;
        results[1] = 0;
    }

    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //This boolean func check if str is number
    protected boolean isNumber(String sNumber)
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




}
