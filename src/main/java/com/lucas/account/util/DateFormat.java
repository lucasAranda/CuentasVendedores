package com.lucas.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maquina0 on 16/08/2016.
 */
public class DateFormat {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    static public Date parseString(String sDate){
        Date result = null;
        try {
            result = dateFormat.parse(sDate);
        } catch (ParseException e) {
            // 31/01/1990
            //result = new Date(633754800000l);
            e.printStackTrace();
        } finally{
            return result;
        }
    }

    public static String formatDate(Date date){
        try{
            return dateFormat.format(date);
        }catch (Exception e){
            return "";
        }

    }

}
