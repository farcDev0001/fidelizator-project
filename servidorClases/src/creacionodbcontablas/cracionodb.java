/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacionodbcontablas;

import basedatos.modelos.Cliente;
import basedatos.modelos.Vendedor;
import basedatos.modelos.Venta;
import seguridadOdb.OdbConnectNCrud;

/**
 *
 * @author Curro
 */
public class cracionodb {
    private final String urlDb = "xlsproductos\\productosini.odb";
    
    public void guardarEjemplos(){
        OdbConnectNCrud odb = new OdbConnectNCrud(urlDb);
        odb.getEntityManagerAndBeginTransaction();
        odb.saveObject(new Cliente("11111111A", "C1", "1@a.com", 0));
        odb.saveObject(new Cliente("22222222C", "C2", "2@b.com", 2));
       odb.saveObject(new Cliente("33333333C","C3","3@b.com",3));
       odb.saveObject(new Cliente("44444444D", "C4", "4@d.com", 4));
       odb.saveObject(new Cliente("55555555E","C5","5@e.com",5));
       
       odb.saveObject(new Vendedor("V1", "JRC", "1234"));
       odb.saveObject(new Vendedor("V2", "ADC", "1234"));
       
       odb.saveObject(new Venta(1,"2018-07-05","19:33:00",220160,"11111111A","V1"));
       odb.saveObject(new Venta(2,"2017-07-05","19:33:00",220160,"22222222C","V2"));
       odb.saveObject(new Venta(3,"2015-07-05","19:33:00",220160,"11111111A","V1"));
       odb.saveObject(new Venta(4,"2018-07-05","19:33:00",220160,"33333333C","V2"));
       odb.saveObject(new Venta(5,"2019-07-05","19:33:00",220160,"11111111A","V1"));
       odb.saveObject(new Venta(6,"2013-07-05","19:33:00",220160,"11111111A","V2"));
       odb.saveObject(new Venta(7,"2015-07-05","19:33:00",220160,"55555555E","V1"));
       
       odb.commitAndCloseEntityManager();
       
    }
}
