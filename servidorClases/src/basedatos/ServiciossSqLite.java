/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import basedatos.modelos.Cliente;
import basedatos.modelos.Producto;
import basedatos.modelos.Vendedor;
import basedatos.modelos.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Curro
 */
public class ServiciossSqLite {
    private final String urlDb = "jdbc:sqlite:database/tienda.db";
    private Connection conexion;
    
    
    
    /**
     * Inserta un producto en la base de datos
     * @param codProducto
     * @param nombre
     * @param pvp
     * @return true si se ha insertado, false si excepcion
     */
    public boolean insertarProducto(int codProducto, String nombre, double pvp){
        try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("INSERT INTO Productos VALUES("+codProducto+",'"+nombre+"',"+pvp+");");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+codProducto);
            return false;
        }
    }
    
    /**
     * Modifica el precio de un producto introduciendo su código
     * @param pvp
     * @param codProducto
     * @return true si se ha modificado, false si excepcion
     */
    public boolean modificaPvpProducto(double pvp, int codProducto){
         try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("UPDATE Productos SET pvp ="+pvp+" WHERE"
                    + " cod_producto ="+codProducto+";");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Borra un producto de la base de datos a partir de su codigo
     * @param codProducto
     * @return true si se ha borrado, false si no
     */
    public boolean borraProducto(int codProducto){
         try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("DELETE FROM Productos"
                    + " WHERE cod_producto="+codProducto+";");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un producto a partir de su codigo
     * @param codProducto
     * @return Producto o null si excepción.
     */
    public Producto getProductoByCodigo(int codProducto){
    try {   
            Producto producto = null;
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Productos"
                    + " WHERE cod_producto="+codProducto+";");
            
            if(resultado.next()){
            producto = new Producto(resultado.getInt(1),resultado.getString(2),
            resultado.getDouble(3));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return producto;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene todos los productos de la base de datos
     * @return ArrayList con productos
     */
    public ArrayList<Producto> getAllProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Productos;");
            
            while(resultado.next()){
                productos.add(new Producto(resultado.getInt(1),
                        resultado.getString(2), resultado.getDouble(3)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return productos;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Busca productos a partir de fragmento de nombre
     * @param fragmentoNombre
     * @return arrayList de Producto
     */
    public ArrayList<Producto> buscarProductos(String fragmentoNombre){
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Productos WHERE"
                    + " nombre LIKE '%"+fragmentoNombre+"%';");
            
            while(resultado.next()){
                productos.add(new Producto(resultado.getInt(1),
                        resultado.getString(2), resultado.getDouble(3)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return productos;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Inserta un cliente en la tabla Clientes
     * @param dni 
     * @param nombre
     * @param email
     * @param puntos
     * @return true si se ha insertado, false si excepcion
     */
    public boolean insertarCliente(String dni, String nombre, String email, int puntos){
     try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("INSERT INTO Clientes VALUES('"+dni+"','"+nombre+"','"
                    +email+"',"+puntos+");");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Modifica los puntos de un cliente introduciendo su codigo
     * @param dni
     * @param puntos nuevos puntos
     * @return true si se modifican, false si excepción
     */
    public boolean modificarPuntosCliente(String dni, int puntos){
    try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("UPDATE Clientes SET puntos ="+puntos+" WHERE"
                    + " dni ="+dni+";");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    } 
    
    /**
     * Borra un cliente introduciendo su codigo
     * @param dni
     * @return true si se ha borrado, false si excepcion
     */
    public boolean borrarCliente(String dni){
         try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("DELETE FROM Clientes"
                    + " WHERE dni="+dni+";");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene lista con todos los clientes
     * @return ArrayList de clientes
     */
    public ArrayList<Cliente> getAllClientes(){
    ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Clientes;");
            
            while(resultado.next()){
                clientes.add(new Cliente(resultado.getString(1),
                    resultado.getString(2), resultado.getString(3), resultado.getInt(4)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return clientes;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene busqueda de clientes a partir de un fragmento de nommbre
     * @param fragmentoNombre
     * @return ArrayList de Clientes
     */
    public ArrayList<Cliente> buscarClientes(String fragmentoNombre){
    ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Clientes WHERE"
                    + " nombre LIKE '%"+fragmentoNombre+"%';");
            
            while(resultado.next()){
                clientes.add(new Cliente(resultado.getString(1),
                    resultado.getString(2), resultado.getString(3), resultado.getInt(4)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return clientes;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Devuelve Cliente introduciendo un código de cliente
     * @param dni
     * @return Cliente
     */
    public Cliente getClienteByCodigo(String dni){
    try {   
            Cliente cliente = null;
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Clientes"
                    + " WHERE dni="+dni+";");
            
            if (resultado.next()){
            cliente = new Cliente(resultado.getString(1),resultado.getString(2),
            resultado.getString(3),resultado.getInt(4));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return cliente;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Inserta un vendedor nuevo en la base de datos
     * @param codVendedor
     * @param nombre
     * @param password
     * @return true si se inserta, false si excepción
     */
    public boolean insertarVendedor(String codVendedor,String nombre,String password){
    try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("INSERT INTO Vendedores VALUES('"+codVendedor+"','"+nombre+"','"+password+"');");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Busca vendedor a partir de su código
     * @param codVendedor
     * @return Vendedor, null si excepcion
     */
    public Vendedor buscarVendedorPorCodigo(String codVendedor){
        try {
            Vendedor vendedor = null;
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Vendedores"
                    + " WHERE cod_vendedor='"+codVendedor+"';");
            
            if (resultado.next()){
            vendedor = new Vendedor(resultado.getString(1),resultado.getString(2),
            resultado.getString(3));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return vendedor;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Devuelve todos los vendedores
     * @return ArrayList con los vendedores
     */
    public ArrayList<Vendedor> getAllVendedores(){
    ArrayList<Vendedor> vendedores = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Vendedores;");
            
            while(resultado.next()){
                vendedores.add(new Vendedor(resultado.getString(1),
                    resultado.getString(2), resultado.getString(3)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return vendedores;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Borra un vendedor a partir de su codVendedor
     * @param codVendedor
     * @return true si se ha borrado, false si excepcion
     */
    public boolean borrarVendedor(String codVendedor){
    try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("DELETE FROM Vendedores"
                    + " WHERE cod_vendedor="+codVendedor+";");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
   
    public boolean insertarVenta(int codVenta, int codProducto, String dniCliente,
            String codVendedor) {
         try {
            conexion = DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            stat.executeUpdate("INSERT INTO Ventas VALUES("+codVenta+",date('now','localtime'),time('now','localtime'),"
                    +codProducto+",'"+dniCliente+"','"+codVendedor+"');");
            stat.close();
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public ArrayList<Venta> getAllVentas(){
    ArrayList<Venta> ventas = new ArrayList<>();
        try {
            conexion= DriverManager.getConnection(urlDb);
            Statement stat = conexion.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM Ventas;");
            
            while(resultado.next()){
                ventas.add(new Venta(resultado.getInt(1),
                    resultado.getString(2), resultado.getString(3),
                        resultado.getInt(4),resultado.getString(5),resultado.getString(6)));
            }
            
            resultado.close();
            stat.close();
            conexion.close();
            return ventas;
                    } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
}
