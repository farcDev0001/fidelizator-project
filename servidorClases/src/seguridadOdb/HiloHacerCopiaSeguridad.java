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

/**
 *
 * @author Curro
 */
public class HiloHacerCopiaSeguridad implements Runnable{
    private final String urlDestino;

    public HiloHacerCopiaSeguridad(String urlDestino) {
        this.urlDestino = urlDestino;
    }
    
    

    @Override
    public void run() {
       OdbConnectNCrud odb = new OdbConnectNCrud(urlDestino);
       odb.getEntityManagerAndBeginTransaction();
       
       for(Producto p : new ServiciossSqLite().getAllProductos()){
           odb.saveObject(p);
       }
       
       for(Cliente c : new ServiciossSqLite().getAllClientes()){
           odb.saveObject(c);
       }
       
       for(Vendedor v : new ServiciossSqLite().getAllVendedores()){
           odb.saveObject(v);
       }
       
       for(Venta v : new ServiciossSqLite().getAllVentas()){
           odb.saveObject(v);
       }
       
       odb.commitAndCloseEntityManager();
    }
}
    
