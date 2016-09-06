package com.lucas.account.service;

import com.lucas.account.model.Comprobante;
import com.lucas.account.model.User;
import com.lucas.account.model.Vendedor;
import com.lucas.account.util.DateFormat;
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

import static com.lucas.account.util.Excel.isRowEmpty;

/**
 * Created by maquina0 on 19/08/2016.
 */
@Component
public class VendedorService {

    @Autowired
    private UserService userService;

    private static String[] archivos = {"H:\\ARCHIVOS EXCEL\\DESARROLLADORA DEL ESTE\\COBRANZAS VENDEDORES VS ESTE.xlsx",
            "H:\\ARCHIVOS EXCEL\\DESARROLLADORA DEL VALLE DE UCO\\COBRANZAS VENDEDORES VS UCO.xlsx",
            "H:\\ARCHIVOS EXCEL\\HORMICON\\COBRANZAS VENDEDORES VS HORMICON.xlsx",
            "H:\\ARCHIVOS EXCEL\\INSUCON\\COBRANZAS INSUCON.xlsx"};

    public List<String> obtenerVendedores(){
        List<String> vendedores = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(archivos[0]);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                vendedores.add(workbook.getSheetName(i));
            }
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
                    if (isRowEmpty(row)) {
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
                        vendedor.setTotalEste(importeParcial);
                        break;
                    case 1:
                        vendedor.setTotalUco(importeParcial);
                        break;
                    case 2:
                        vendedor.setTotalHormicon(importeParcial);
                        break;
                    case 3:
                        vendedor.setTotalInsucon(importeParcial);
                        break;
                }
                vendedor.setMontoTotal(importeTotal);
                vendedor.setComprobantes(comprobantes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vendedor;
    }

    public Vendedor obtenerComprobantesPorSheet(String sheet){

        return TERMINAR;
    }
}
