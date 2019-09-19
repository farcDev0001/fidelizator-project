/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exportarOds;

import basedatos.ServiciossSqLite;
import basedatos.modelos.Producto;
import basedatos.modelos.Venta;
import java.util.ArrayList;
import static org.apache.poi.hssf.record.aggregates.RowRecordsAggregate.createRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Curro
 */
public class VentasDeUnProducto {
    
    private final ArrayList<Venta> ventas;
    private final Producto producto;
    
    public VentasDeUnProducto(ArrayList<Venta> ventas){
        this.ventas = ventas;
        this.producto = new ServiciossSqLite().getProductoByCodigo(ventas.get(0).getCodProducto());
    }
    
    public Workbook generarLibro(){
        Workbook libro = new XSSFWorkbook();
        Sheet pagina = libro.createSheet("Informe de ventas de Producto");
        
        
        return libro;
    }
    
    /*
    public Sheet generarDatosProducto(Sheet pagina){
        Sheet hoja = pagina;
        Row fila = new createRow(0);
        return hoja;
    }*/
}
