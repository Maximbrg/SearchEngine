package Parser.ParserRuls;

import java.util.ArrayList;

public class PriceRepresentationRule extends ANumberRules {

    public int[] roleChecker(ArrayList<String> words, String key) {
            results[0] = 1;
            results[1] = 1;
            int index=0;
        boolean hasComma = false;
        boolean flag=false;
        double price = 0;
        boolean bMultiplier = false;
        String word = words.get(index);


        if (word.contains(",")) {
            hasComma = true;
            String tempWord = "";
            for (int k = 0; k < word.length(); k++) {
                if (!(word.charAt(k) == ',')) {
                    tempWord = tempWord + word.charAt(k);
                }

            }
            word = tempWord;
        }

        String tempWord= words.get(index);
        if (index < words.size() - 1 && (isNumber(words.get(index))||(tempWord.charAt(tempWord.length()-1))=='m')) {

            if((tempWord.charAt(tempWord.length()-1))=='m'){
                price = Double.parseDouble(word.substring(0,tempWord.length()-1));

            }
            else
                price = Double.parseDouble(word);

            if (index < words.size() - 1) {
                if (words.get(index + 1) == "million" || words.get(index + 1) == "m" || words.get(index + 1) == "Million") {
                    bMultiplier = true;
                } else if (words.get(index + 1) == "billion" || words.get(index + 1) == "bn" || words.get(index + 1) == "Billion") {
                    price = price * 1000;
                    bMultiplier = true;
                }
                if (bMultiplier && index < words.size() - 2 && words.get(index + 2) == "U.S.")
                    index += 2;
                if (index < words.size() - 1 && words.get(index + 1).contains("/")){
                    index += 1;
                    flag = true;
                }


                if (index < words.size() - 1 && words.get(index + 1) != "dollars" && words.get(index + 1) != "Dollars")
                    return results;
                index++;
            }
        } else if (words.get(index).charAt(0) == '$' && words.get(index).length() > 1 && isNumber(words.get(index).substring(1))) {
            price = Double.parseDouble(word.substring(1));

            if (index < words.size() - 1) {

                if (words.get(index + 1) == "million" || words.get(index + 1) == "Million") {
                    bMultiplier = true;
                } else if (words.get(index + 1) == "billion" || words.get(index + 1) == "Billion") {
                    price = price * 1000;
                    bMultiplier = true;
                } else if (words.get(index + 1) == "trillion" || words.get(index + 1) == "Trillion") {
                    price = price * 1000000;
                    bMultiplier = true;
                }
            }
        } else
            return results;
        if (bMultiplier)
            index++;
        if (!bMultiplier && price >= 1000000) {
            //make it to its representation
            price = price / 1000000;
            // we keep only 2 digits after the dot.
            int roundOff;
            roundOff = (int) (price * 100);
            price = ((double) roundOff) / 100;
            bMultiplier = true;
        }

        if (bMultiplier) {
            String strPrice2=String.valueOf(price);
            int newPrice=0;
            if(strPrice2.contains(".0")) {
                price = Double.parseDouble(strPrice2);
                newPrice = (int) price;
                addToDictionary(String.valueOf(newPrice) + " M Dollars",key);

            }
            else{
                addToDictionary(String.valueOf(price) + " M Dollars",key);
            }

        } else {
            StringBuilder str = new StringBuilder(String.valueOf(price).length() + 9);
            if (price >= 1000 && hasComma)
            {
                String strPrice=String.valueOf(price);
                if(strPrice.contains(".0")){
                    price = Double.parseDouble(strPrice);
                    int newPrice=(int)price;

                    str.append((String.valueOf(((int) (newPrice / 1000)) + ",")));
                    if (newPrice % 1000 >= 100)
                        str.append((String.valueOf(((newPrice % 1000)) + " Dollars")));
                    else if (newPrice % 100 >= 10)
                        str.append((String.valueOf(("0" + (newPrice % 100)) + " Dollars")));
                    else
                        str.append((String.valueOf(("00" + (newPrice % 100)) + " Dollars")));


                }
                else{
                    str.append((String.valueOf(((int) (price / 1000)) + ",")));
                    if (price % 1000 >= 100)
                        str.append((String.valueOf(((price % 1000)) + " Dollars")));
                    else if (price % 100 >= 10)
                        str.append((String.valueOf(("0" + (price % 100)) + " Dollars")));
                    else
                        str.append((String.valueOf(("00" + (price % 100)) + " Dollars")));
                }

            } else {
                if(flag){
                    String strPrice=String.valueOf(price);
                    if(strPrice.contains(".0")){
                        price = Double.parseDouble(strPrice);
                        int newPrice=(int)price;
                        strPrice=String.valueOf(newPrice);

                    }
                    String fraction= words.get(index-1);
                    str.append((strPrice+" " ));
                    str.append((fraction +" Dollars"));
                }
                else{
                    str.append((String.valueOf(price) + " Dollars"));
                }


            }
            addToDictionary(String.valueOf(str),key);
        }
        index++;
        results[0]=1;
        results[1]=index;
        return results;
    }
    }

