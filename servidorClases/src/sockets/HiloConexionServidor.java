/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import basedatos.ServiciossSqLite;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Curro
 */
public class HiloConexionServidor implements Runnable{
    
    private Socket cliente;
    private ServerSocket servidor;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    
    public HiloConexionServidor(Socket cliente, ServerSocket servidor){
        try {
            this.servidor = servidor;
            this.cliente = cliente;
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(HiloConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        String comando = "";
        try {
            comando = (String) entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al leer el comando");
        }
        
        switch(comando){
            case "1":
        {
            try {
                salida.writeObject(new ServiciossSqLite().getAllProductos());
                cliente.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            default:
                break;
        }
    }
    
}
