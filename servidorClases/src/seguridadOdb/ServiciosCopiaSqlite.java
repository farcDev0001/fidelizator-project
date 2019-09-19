/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridadOdb;

import basedatos.ServiciossSqLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Curro
 */
public class ServiciosCopiaSqlite {
    private Connection conexion;
    private Statement stat;
    private final String urlDb = "jdbc:sqlite:database/tienda.db";
    
    public boolean openConection(){
        try {
            conexion = DriverManager.getConnection(urlDb);
            stat = conexion.createStatement();      
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean borrarDatos(){
        try {
            stat.executeUpdate("DELETE FROM Ventas");
            stat.executeUpdate("DELETE FROM Clientes");
            stat.executeUpdate("DELETE FROM Vendedores");
            stat.executeUpdate("DELETE FROM Productos");
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void insertarProducto(int codProducto, String nombre, double pvp){
        try {
            stat.executeUpdate("INSERT INTO Productos VALUES("+codProducto+",'"+nombre+"',"+pvp+");");
        } catch (SQLException ex) {
            System.out.println(codProducto);
        }
    }
    
    public boolean insertarCliente(String dni, String nombre, String email, int puntos){
     try {
            stat.executeUpdate("INSERT INTO Clientes VALUES('"+dni+"','"+nombre+"','"
                    +email+"',"+puntos+");");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean insertarVendedor(String codVendedor,String nombre,String password){
    try {
            stat.executeUpdate("INSERT INTO Vendedores VALUES('"+codVendedor+"','"+nombre+"','"+password+"');");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean insertarVenta(int codVenta, String fecha, String hora, int codProducto, String dniCliente,
            String codVendedor) {
         try {
            stat.executeUpdate("INSERT INTO Ventas VALUES("+codVenta+",'"+fecha+"','"+hora+"',"
                    +codProducto+",'"+dniCliente+"','"+codVendedor+"');");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public void closeConnection(){
        try {
            stat.close();
            conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiciossSqLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
