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
public class Cliente implements Serializable {
    @Id
    private String dni;
    private String nombre;
    private String email;
    private int puntos;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String email, int puntos) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.puntos = puntos;
    }

    public String getDni() {
        return dni;
    }

    public void setCodigo(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    
}
