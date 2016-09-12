package com.lucas.account.util;

/**
 * Created by maquina0 on 08/09/2016.
 */
public class Rounder {

    public static Double round (Double numberToRound, int decimalPlaces){
        Double value = 0.0;

        if (decimalPlaces == 0){
            value= Math.rint(numberToRound *1)/1;
        } else if (decimalPlaces == 1){
            value= Math.rint(numberToRound *10)/10;
        }else if (decimalPlaces == 2){
            value= Math.rint(numberToRound *100)/100;
        }else if (decimalPlaces == 3){
            value= Math.rint(numberToRound *1000)/1000;
        }

        return value;
    }

}
