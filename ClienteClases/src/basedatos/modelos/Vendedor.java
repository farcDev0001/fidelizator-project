package basedatos.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Curro
 */
@Entity
public class Vendedor implements Serializable{
    @Id
    private String codVendedor;
    private String nombre;
    private String password;

    public Vendedor() {
    }

    public Vendedor(String codigo, String nombre, String password) {
        this.codVendedor = codigo;
        this.nombre = nombre;
        this.password = password;
    }

    public String getCodigo() {
        return codVendedor;
    }

    public void setCodigo(String codigo) {
        this.codVendedor = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
