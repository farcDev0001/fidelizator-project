/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Curro
 */
public class HiloCrearSqlite implements Runnable {
    
    private final String url = "jdbc:sqlite:database/tienda.db";
    private Connection conexion;
    
    
    @Override
    public void run(){
       if (!new File("database/tienda.db").exists())
        try {
            conexion = DriverManager.getConnection(url);
            
            String sqlString = "CREATE TABLE IF NOT EXISTS Productos("
            +"cod_producto INTEGER PRIMARY KEY,"
            +"nombre TEXT,"
            +"pvp REAL"
            +");";
            
            Statement stat = conexion.createStatement();
            stat.execute(sqlString);
           
            sqlString = "CREATE TABLE IF NOT EXISTS Clientes("
            +"dni TEXT PRIMARY KEY,"
            +"nombre TEXT,"
            +"email TEXT,"
            +"puntos INTEGER"
            +");";
            
            stat.execute(sqlString);
           
            sqlString = "CREATE TABLE IF NOT EXISTS Vendedores("
            +"cod_vendedor TEXT PRIMARY KEY,"
            +"nombre TEXT,"
            +"password TEXT"
            +");";
            
            stat.execute(sqlString);
            
            sqlString = "CREATE TABLE IF NOT EXISTS Ventas("
            +"cod_venta INTEGER PRIMARY KEY,"
            +"fecha DATE NOT NULL,"
            +"hora TIME NOT NULL,"
            +"cod_producto INTEGER NOT NULL  REFERENCES Productos(cod_producto),"
            +"dni_cliente TEXT NOT NULL REFERENCES Clientes(dni),"
            +"cod_vendedor TEXT NOT NULL REFERENCES Vendedores(cod_vendedor)"
            +");";
            
            stat.execute(sqlString);
            stat.close();
            
            conexion.close();
            System.out.println("Base de datos tienda.db creada");
        }
        
        catch(SQLException e){
           System.out.println(e.getMessage()+"\nFallo");
           
        }
        
    }
}
