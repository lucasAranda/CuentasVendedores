package com.lucas.account.util;

import org.apache.poi.ss.usermodel.Row;

/**
 * Created by maquina0 on 17/08/2016.
 */
public class Excel {
    public static boolean isRowEmpty(Row row){
        if (row.getCell(2) == null || row.getCell(2).getStringCellValue().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isRowEmptyAdm(Row row) {
        if (row.getCell(0) == null || row.getCell(0).getStringCellValue().equals("")) {
            return true;
        } else if (row.getCell(2) == null || row.getCell(2).getStringCellValue().equals("")) {
            return true;
        } else if (row.getCell(7) == null || row.getCell(7).getCellType() == 3) {
            return true;
        }
        return false;
    }
}
