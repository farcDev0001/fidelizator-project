/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorclases;

import basedatos.HiloCrearSqlite;
import basedatos.modelos.Producto;
import basedatos.ServiciossSqLite;
import basedatos.modelos.Cliente;
import basedatos.modelos.Vendedor;
import creacionodbcontablas.cracionodb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import seguridadOdb.HiloHacerCopiaSeguridad;
import seguridadOdb.HiloRestauraCopiaSeguridad;
import seguridadOdb.OdbConnectNCrud;
import sockets.SocketServidor;
import xls.LecturaYguardadoXlsOdb;

/**
 *
 * @author Curro
 */
public class ServidorClases {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
       
      
     /* Thread hiloRestaura = new Thread(new HiloRestauraCopiaSeguridad(new OdbConnectNCrud("copia.odb")));
      hiloRestaura.join();
      hiloRestaura.start();*/
     
        new SocketServidor().startServidor();
        }
      
   
}
