/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Curro
 */

@Entity
public class Producto implements Serializable {
    @Id
    private int codProducto;
    private String nombre;
    private double pvp;

    public Producto(int codigo, String nombre, double pvp) {
        this.codProducto = codigo;
        this.nombre = nombre;
        this.pvp = pvp;
    }

    public Producto() {
        this.codProducto = 0;
        this.nombre = null;
        this.pvp = 0;
    }

    
    
    public int getCodigo() {
        return codProducto;
    }

    public void setCodigo(int codigo) {
        this.codProducto = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
    
    
}
