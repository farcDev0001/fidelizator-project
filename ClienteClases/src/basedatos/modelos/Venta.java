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
public class Venta implements Serializable {
    @Id
    private int codVenta;
    private String fecha;
    private String hora;
    private int codProducto;
    private String dniCliente;
    private String codVendedor;

    public Venta() {
    }

    public Venta(int codVenta, String fecha, String hora, int codProducto, String dniCliente, String codVendedor) {
        this.codVenta = codVenta;
        this.fecha = fecha;
        this.hora = hora;
        this.codProducto = codProducto;
        this.dniCliente = dniCliente;
        this.codVendedor = codVendedor;
    }

    public int getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String codCliente) {
        this.dniCliente = codCliente;
    }

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }
    
    
}
