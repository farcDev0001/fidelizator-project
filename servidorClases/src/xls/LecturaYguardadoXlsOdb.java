/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xls;

import basedatos.modelos.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import seguridadOdb.OdbConnectNCrud;

/**
 *
 * @author Curro
 */
public class LecturaYguardadoXlsOdb {
    private final String urlXls = "xlsproductos\\productocompleto.xls";
    private final String urlDb = "xlsproductos\\productosini.odb";

    public boolean lectura(){
        try {
            OdbConnectNCrud guardarObjeto = new OdbConnectNCrud(urlDb);
            FileInputStream fin = new FileInputStream(new File(urlXls));
            
            //Creacion objeto que tendrá el libro de Excel
            HSSFWorkbook libro = new HSSFWorkbook(fin);
            
            //Obtengo la hoja
            
            HSSFSheet hoja = libro.getSheetAt(0);
            
            //Obtencion del iterador
            Iterator<Row> rowIterator = hoja.iterator();
            
            Row row;
            int contador = 0;
            
            guardarObjeto.getEntityManagerAndBeginTransaction();
            
            while (rowIterator.hasNext()){
                contador++;
                row = rowIterator.next();
                
                //Iterador de todas las celdas de una fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                Producto producto = new Producto();
                
                if (cellIterator.hasNext()){
                
                   for(int i=0; i<3; i++){
                    
                   celda = cellIterator.next();
                 
                   switch (i){
                       case 0:
                           producto.setCodigo((int) celda.getNumericCellValue());
                           break;
                       case 1:
                           producto.setNombre(validaString(celda.getStringCellValue()));
                           break;
                       case 2:
                           producto.setPvp(celda.getNumericCellValue());
                           break;
                   }
                  }
                 guardarObjeto.saveObject(producto);
                }    
            }
            guardarObjeto.commitAndCloseEntityManager();
            libro.close();
            fin.close();
            
            System.out.println("Lineas leidas: " + contador);
            return true;
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
           return false;
        }
    }
    
    private String validaString(String nombre){
        String nombreValidado = nombre;
        if (nombreValidado.contains("'")){
            nombreValidado = nombreValidado.replace("'", " ");
            
        }
        return nombreValidado;
    } 
}
