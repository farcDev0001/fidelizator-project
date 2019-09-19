/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteclases;

import basedatos.modelos.Producto;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Curro
 */
public class Cliente {
    private Socket cliente;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Scanner teclado;
    
    public Cliente (){
        try {
            cliente = new Socket("localhost", 9191);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            teclado = new Scanner(System.in);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getProductos(){
        try {
            salida.writeObject(teclado.nextLine());
            int contador = 0;
            for (Producto p : (ArrayList<Producto>) entrada.readObject()){
                System.out.println(p.getNombre());
                contador++;
            } 
        System.out.println("Numero de productos: "+contador);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
