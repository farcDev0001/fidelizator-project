/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridadOdb;

import basedatos.ServiciossSqLite;
import basedatos.modelos.Cliente;
import basedatos.modelos.Producto;
import basedatos.modelos.Vendedor;
import basedatos.modelos.Venta;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Curro
 */
public class HiloRestauraCopiaSeguridad implements Runnable {
        
    private OdbConnectNCrud odb;
    private ServiciosCopiaSqlite servicios;

    public HiloRestauraCopiaSeguridad(OdbConnectNCrud odb) {
        this.servicios = new ServiciosCopiaSqlite();
        this.odb = odb;
    }

    
    @Override
    public void run() {
        odb.getEntityManagerAndBeginTransaction();
        servicios.openConection();
        servicios.borrarDatos();
        
        for(Producto p : odb.getProductos()){
            servicios.insertarProducto(p.getCodigo(), 
                    p.getNombre(), p.getPvp());
        }
        
        for(Cliente c : odb.getClientes()){
            servicios.insertarCliente(c.getDni(), c.getNombre(), 
                    c.getEmail(), c.getPuntos());
        }
        
        for(Vendedor v : odb.getVendedores()){
            servicios.insertarVendedor(v.getCodigo(), v.getNombre(), 
                    v.getPassword());
        }
        
        for(Venta v : odb.getVentas()){
            servicios.insertarVenta(v.getCodVenta(),v.getFecha(),v.getHora(),
                    v.getCodProducto(), v.getDniCliente(), v.getCodVendedor());
        }
        servicios.closeConnection();
        odb.commitAndCloseEntityManager();
        odb = null;
        servicios = null;
        
    }
    
    
    
}
