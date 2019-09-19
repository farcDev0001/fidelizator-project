/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Curro
 */
public class SocketServidor {
    private ServerSocket servidor;
    private Socket cliente;
    
    public SocketServidor(){
        try {
            servidor = new ServerSocket (9191);
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startServidor(){
        int contador = 0;
        
        while (true){
            try {
                cliente = servidor.accept();
                contador++;
                System.out.println("Clientes Conectados: "+contador);
                new Thread(new HiloConexionServidor(cliente, servidor)).start();
            } catch (IOException ex) {
                Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
