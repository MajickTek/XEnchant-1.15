package com.majicktek.xenchant.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marsalisqualls on 7/13/17.
 */
public class RomanNumeralUtility {

    private Map<Integer, String> romanNumerals;

    public RomanNumeralUtility(){
        romanNumerals = new LinkedHashMap<>();

        romanNumerals.put(1000, "M");
        romanNumerals.put(900, "CM");
        romanNumerals.put(500, "D");
        romanNumerals.put(400, "CD");
        romanNumerals.put(100, "C");
        romanNumerals.put(90, "XC");
        romanNumerals.put(50, "L");
        romanNumerals.put(40, "XL");
        romanNumerals.put(10 , "X");
        romanNumerals.put(9, "IX");
        romanNumerals.put(5, "V");
        romanNumerals.put(4, "IV");
        romanNumerals.put(1, "I");
    }

    public String convertToRoman(int decimalNumber) {
        String romanNumber = "";

        for(Integer key : romanNumerals.keySet()){
            while(decimalNumber >= key){
                romanNumber += romanNumerals.get(key);
                decimalNumber -= key;
            }
        }

        return romanNumber;
    }

    public int ConvertToArabic(String romanNumber){
        int arabic = 0, previousNumber = 0, currentNumber = 0;

        for(int x = 0; x < romanNumber.length(); x++){
            String character = Character.toString(romanNumber.charAt(x));
            currentNumber = getKeyFromValue(character);
            if(currentNumber > previousNumber && previousNumber > 0)
                arabic -= 2 * previousNumber;
            previousNumber = currentNumber;
            arabic += currentNumber;
        }

        return arabic;
    }

    private int getKeyFromValue(String character){
        Set s = romanNumerals.entrySet();
        Iterator it = s.iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            if(character.equalsIgnoreCase((String)entry.getValue())){
                return (int) entry.getKey();
            }
        }
        return 0;
    }
}