package com.lucas.account.service;

import com.lucas.account.model.Adimix;
import com.lucas.account.model.Comprobante;
import com.lucas.account.model.User;
import com.lucas.account.model.Vendedor;
import com.lucas.account.util.DateFormat;
import com.lucas.account.util.Excel;
import com.lucas.account.util.Rounder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by maquina0 on 19/08/2016.
 */
@Component
public class VendedorService {

    @Autowired
    private UserService userService;

    /*private static String[] archivos = {"H:\\ARCHIVOS EXCEL\\DESARROLLADORA DEL ESTE\\COBRANZAS VENDEDORES VS ESTE.xlsx",
            "H:\\ARCHIVOS EXCEL\\DESARROLLADORA DEL VALLE DE UCO\\COBRANZAS VENDEDORES VS UCO.xlsx",
            "H:\\ARCHIVOS EXCEL\\HORMICON\\COBRANZAS VENDEDORES VS HORMICON.xlsx",
            "H:\\ARCHIVOS EXCEL\\INSUCON\\COBRANZAS INSUCON.xlsx"};
    private static String archAdm = "H:\\ARCHIVOS EXCEL\\INFORME DIARIO ADIMIX.xlsx";*/

    /*private static String[] archivos = {"E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\DESARROLLADORA DEL ESTE\\COBRANZAS VENDEDORES VS ESTE.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\DESARROLLADORA DEL VALLE DE UCO\\COBRANZAS VENDEDORES VS UCO.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\HORMICON\\COBRANZAS VENDEDORES VS HORMICON.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\INSUCON\\COBRANZAS INSUCON.xlsx"};*/

    private static String[] archivos = {"E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\SistemaLucas\\DESARROLLADORA DEL ESTE\\COBRANZAS VENDEDORES VS ESTE.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\SistemaLucas\\DESARROLLADORA DEL VALLE DE UCO\\COBRANZAS VENDEDORES VS UCO.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\SistemaLucas\\HORMICON\\COBRANZAS VENDEDORES VS HORMICON.xlsx",
            "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\SistemaLucas\\INSUCON\\COBRANZAS INSUCON.xlsx"};
    private static String archAdm = "E:\\DISK\\datos\\Jcalabretto\\COBRANZAS\\SistemaLucas\\INFORME DIARIO ADIMIX.xlsx";

    public List<String> obtenerVendedores(){
        List<String> vendedores = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(archivos[0]);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                vendedores.add(workbook.getSheetName(i));
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendedores;
    }

    public Vendedor obtenerComprobantes(String usuarioVendedor){

        System.out.println(usuarioVendedor);
        User user = userService.findByUserName(usuarioVendedor);
        if (user == null) System.out.println("Usuario Nulo");
        double importeParcial, importeTotal = 0;

        List<Comprobante> comprobantes = new ArrayList<>();

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(user.getNombre());

        for (int j = 0; j < archivos.length; j++) {
            importeParcial = 0;
            try {
                //List<UserInfo> lstUser = new ArrayList<>();
                int i = 1;
                // Creates a workbook object from the uploaded excelfile
                //XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
                XSSFWorkbook workbook = new XSSFWorkbook(archivos[j]);
                // Creates a worksheet object representing the first sheet
                //XSSFSheet worksheet = workbook.getSheetAt(0);
                XSSFSheet worksheet = workbook.getSheet(user.getSheet());
                Iterator<Row> rowIterator = worksheet.iterator();
                // Reads the data in excel file until last row is encountered
                Row row;
                row = rowIterator.next();
                //row = rowIterator.next();
                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    if (Excel.isRowEmpty(row)) {
                        continue;
                    }
                    Comprobante comprobante = new Comprobante();
                    //System.out.println("Comprobante: " + (i++));
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        switch (cell.getColumnIndex()) {
                            case 2:
                                //System.out.println("COD_CLIENT: " + cell.getStringCellValue());
                                break;
                            case 3:
                                comprobante.setCliente(cell.getStringCellValue());
                                //System.out.println("RAZON_SOCI: " + cell.getStringCellValue());
                                break;
                            case 4:
                                comprobante.setFechaComprobante(DateFormat.formatDate(cell.getDateCellValue()));
                                //System.out.println("FECHA: " + cell.getDateCellValue());
                                break;
                            case 5:
                                comprobante.setTipoComprobante(cell.getStringCellValue());
                                //System.out.println("TIPO_COMPROBANTE: " + cell.getStringCellValue());
                                break;
                            case 6:
                                comprobante.setNumeroComprobante(cell.getStringCellValue());
                                //System.out.println("NRO_COMPROBANTE: " + cell.getStringCellValue());
                                break;
                            case 7:
                                /*if (cell.getCellType() == 1){
                                    System.out.println("FECHA_PAGO: ");
                                }else {
                                    System.out.println("FECHA_PAGO: " + cell.getDateCellValue());
                                }*/
                                break;
                            case 8:
                                if (cell.getCellType() == 1) {
                                    System.out.println("OLE");
                                    break;
                                } else {

                                    comprobante.setImporte(cell.getNumericCellValue());
                                    importeParcial += cell.getNumericCellValue();
                                    importeTotal += cell.getNumericCellValue();
                                    //System.out.println("IMPORTE: " + cell.getNumericCellValue());
                                }
                                break;
                            default:
                                break;
                        }//end switch
                        switch (j){
                            case 0:
                                comprobante.setEmpresa("DESAR. DEL ESTE");
                                break;
                            case 1:
                                comprobante.setEmpresa("DESAR. VALLE DE UCO");
                                break;
                            case 2:
                                comprobante.setEmpresa("HORMICON");
                                break;
                            case 3:
                                comprobante.setEmpresa("INSUCON");
                                break;
                        }
                    }//end while
                    comprobantes.add(comprobante);
                    //System.out.println("---------");
                }//end
                workbook.close();
                switch (j){
                    case 0:
                        vendedor.setTotalEste(Rounder.round(importeParcial, 2));
                        break;
                    case 1:
                        vendedor.setTotalUco(Rounder.round(importeParcial, 2));
                        break;
                    case 2:
                        vendedor.setTotalHormicon(Rounder.round(importeParcial, 2));
                        break;
                    case 3:
                        vendedor.setTotalInsucon(Rounder.round(importeParcial, 2));
                        break;
                }
                vendedor.setMontoTotal(Rounder.round(importeTotal, 2));
                vendedor.setComprobantes(comprobantes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vendedor;
    }

    public Vendedor obtenerComprobantesPorSheet(String sheet){
        System.out.println("VENDEDOR: " + sheet);
        double importeParcial, importeTotal = 0;

        List<Comprobante> comprobantes = new ArrayList<>();

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(sheet);

        for (int j = 0; j < archivos.length; j++) {
            importeParcial = 0;
            try {
                //List<UserInfo> lstUser = new ArrayList<>();
                int i = 1;
                // Creates a workbook object from the uploaded excelfile
                //XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
                XSSFWorkbook workbook = new XSSFWorkbook(archivos[j]);
                // Creates a worksheet object representing the first sheet
                //XSSFSheet worksheet = workbook.getSheetAt(0);
                XSSFSheet worksheet = workbook.getSheet(sheet);
                Iterator<Row> rowIterator = worksheet.iterator();
                // Reads the data in excel file until last row is encountered
                Row row;
                row = rowIterator.next();
                //row = rowIterator.next();
                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    if (Excel.isRowEmpty(row)) {
                        continue;
                    }
                    Comprobante comprobante = new Comprobante();
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        switch (cell.getColumnIndex()) {
                            case 2:
                                break;
                            case 3:
                                comprobante.setCliente(cell.getStringCellValue());
                                break;
                            case 4:
                                comprobante.setFechaComprobante(DateFormat.formatDate(cell.getDateCellValue()));
                                break;
                            case 5:
                                comprobante.setTipoComprobante(cell.getStringCellValue());
                                break;
                            case 6:
                                comprobante.setNumeroComprobante(cell.getStringCellValue());
                                break;
                            case 7:
                                break;
                            case 8:
                                if (cell.getCellType() == 1) {
                                    System.out.println("OLE");
                                    break;
                                } else {

                                    comprobante.setImporte(cell.getNumericCellValue());
                                    importeParcial += cell.getNumericCellValue();
                                    importeTotal += cell.getNumericCellValue();
                                }
                                break;
                            default:
                                break;
                        }//end switch
                        switch (j){
                            case 0:
                                comprobante.setEmpresa("DESAR. DEL ESTE");
                                break;
                            case 1:
                                comprobante.setEmpresa("DESAR. VALLE DE UCO");
                                break;
                            case 2:
                                comprobante.setEmpresa("HORMICON");
                                break;
                            case 3:
                                comprobante.setEmpresa("INSUCON");
                                break;
                        }
                    }//end while
                    comprobantes.add(comprobante);
                }//end
                workbook.close();
                System.out.println("CERRAR ARCHIVO");
                switch (j){
                    case 0:
                        vendedor.setTotalEste(Rounder.round(importeParcial, 2));
                        break;
                    case 1:
                        vendedor.setTotalUco(Rounder.round(importeParcial, 2));
                        break;
                    case 2:
                        vendedor.setTotalHormicon(Rounder.round(importeParcial, 2));
                        break;
                    case 3:
                        vendedor.setTotalInsucon(Rounder.round(importeParcial, 2));
                        break;
                }
                vendedor.setMontoTotal(Rounder.round(importeTotal, 2));
                vendedor.setComprobantes(comprobantes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vendedor;
    }

    public List<Adimix> obtenerAdimixSheet(String vendedor) {

        XSSFWorkbook workbook = null;
        List<Adimix> adimixList = new ArrayList<>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(archAdm);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet("DETALLE ADIMIX");
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            //apartir de la tercera fila hay datos validos
            Row row;
            row = rowIterator.next();
            //row = rowIterator.next();
            fin:
            while (rowIterator.hasNext()) {

                row = rowIterator.next();
                System.out.println("Fila: " + row.getRowNum());
                if (Excel.isRowEmptyAdm(row)) {
                    continue;
                }

                Adimix adimix = new Adimix();
                /*System.out.println("Comprobante: " + (i++));
                System.out.println("Cantidad: " + row.getLastCellNum());*/
                //For each row, iterate through all the columns
                outer:
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                    if (cell == null) {
                        //System.out.println("Vacio");
                    } else {

                        switch (cell.getColumnIndex()) {
                            case 0:
                                if (cell.getStringCellValue().equals("FIN")) {
                                    System.out.println("ACA");
                                    break fin;
                                } else if (cell.getStringCellValue().equals(vendedor)) {
                                    System.out.println("VENDEDOR: " + cell.getStringCellValue());
                                    break;
                                } else {
                                    break outer;
                                }
                            case 2:
                                System.out.println("CLIENTE: " + cell.getStringCellValue());
                                adimix.setCliente(cell.getStringCellValue());
                                break;
                            case 3:
                                System.out.println("ARTICULO: " + cell.getStringCellValue());
                                adimix.setArticulo(cell.getStringCellValue());
                                break;
                            case 4:
                                if (cell.getCellType() == 0) {
                                    System.out.println("METROS: " + cell.getNumericCellValue());
                                    adimix.setMetros(cell.getNumericCellValue());
                                    break;
                                } else {
                                    System.out.println("METROS: " + cell.getStringCellValue());
                                    break;
                                }
                            case 5:
                                if (cell.getCellType() == 0) {
                                    System.out.println("PRECIO: " + cell.getNumericCellValue());
                                    adimix.setPrecio(cell.getNumericCellValue());
                                } else {
                                    System.out.println("PRECIO: " + cell.getStringCellValue());
                                }
                                break;
                            case 7:
                                if (cell.getCellType() == 0) {
                                    System.out.println("FECHA_FAC: " + cell.getDateCellValue());
                                    adimix.setFecha(DateFormat.formatDate(cell.getDateCellValue()));
                                } else {
                                    System.out.println("FECHA_FAC: " + cell.getStringCellValue());
                                    adimix.setFecha(cell.getStringCellValue());
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == 0 || cell.getCellType() == 2) {
                                    System.out.println("IMPORTE: " + cell.getNumericCellValue());
                                    adimix.setImporte(cell.getNumericCellValue());
                                } else {
                                    System.out.println("IMPORTE: " + cell.getStringCellValue());
                                }
                                break;
                            default:
                                break;
                        }//end switch
                    }//end if vacio
                }//end for
                if (adimix.getCliente() != null && !(adimix.getCliente().equals(""))){
                    adimixList.add(adimix);
                }
                System.out.println("---------");
            }//end while
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return adimixList;

    }

    public List<Adimix> obtenerAdimix(String username) {

        User user = userService.findByUserName(username);
        if (user == null) System.out.println("Usuario Nulo");

        XSSFWorkbook workbook = null;
        List<Adimix> adimixList = new ArrayList<>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(archAdm);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet("DETALLE ADIMIX");
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            //apartir de la tercera fila hay datos validos
            Row row;
            row = rowIterator.next();
            //row = rowIterator.next();
            fin:
            while (rowIterator.hasNext()) {

                row = rowIterator.next();
                System.out.println("Fila: " + row.getRowNum());
                if (Excel.isRowEmptyAdm(row)) {
                    continue;
                }

                Adimix adimix = new Adimix();
                /*System.out.println("Comprobante: " + (i++));
                System.out.println("Cantidad: " + row.getLastCellNum());*/
                //For each row, iterate through all the columns
                outer:
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                    if (cell == null) {
                        //System.out.println("Vacio");
                    } else {

                        switch (cell.getColumnIndex()) {
                            case 0:
                                if (cell.getStringCellValue().equals("FIN")) {
                                    System.out.println("ACA");
                                    break fin;
                                } else if (cell.getStringCellValue().equals(user.getSheet())) {
                                    System.out.println("VENDEDOR: " + cell.getStringCellValue());
                                    break;
                                } else {
                                    break outer;
                                }
                            case 2:
                                System.out.println("CLIENTE: " + cell.getStringCellValue());
                                adimix.setCliente(cell.getStringCellValue());
                                break;
                            case 3:
                                System.out.println("ARTICULO: " + cell.getStringCellValue());
                                adimix.setArticulo(cell.getStringCellValue());
                                break;
                            case 4:
                                if (cell.getCellType() == 0) {
                                    System.out.println("METROS: " + cell.getNumericCellValue());
                                    adimix.setMetros(cell.getNumericCellValue());
                                    break;
                                } else {
                                    System.out.println("METROS: " + cell.getStringCellValue());
                                    break;
                                }
                            case 5:
                                if (cell.getCellType() == 0) {
                                    System.out.println("PRECIO: " + cell.getNumericCellValue());
                                    adimix.setPrecio(cell.getNumericCellValue());
                                } else {
                                    System.out.println("PRECIO: " + cell.getStringCellValue());
                                }
                                break;
                            case 7:
                                if (cell.getCellType() == 0) {
                                    System.out.println("FECHA_FAC: " + cell.getDateCellValue());
                                    adimix.setFecha(DateFormat.formatDate(cell.getDateCellValue()));
                                } else {
                                    System.out.println("FECHA_FAC: " + cell.getStringCellValue());
                                    adimix.setFecha(cell.getStringCellValue());
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == 0 || cell.getCellType() == 2) {
                                    System.out.println("IMPORTE: " + cell.getNumericCellValue());
                                    adimix.setImporte(cell.getNumericCellValue());
                                } else {
                                    System.out.println("IMPORTE: " + cell.getStringCellValue());
                                }
                                break;
                            default:
                                break;
                        }//end switch
                    }//end if vacio
                }//end for
                if (adimix.getCliente() != null && !(adimix.getCliente().equals(""))){
                    adimixList.add(adimix);
                }
                System.out.println("---------");
            }//end while
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return adimixList;
    }
}
