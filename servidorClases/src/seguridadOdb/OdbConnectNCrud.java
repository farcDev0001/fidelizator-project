/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridadOdb;

import basedatos.modelos.Cliente;
import basedatos.modelos.Producto;
import basedatos.modelos.Vendedor;
import basedatos.modelos.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Curro
 */
public class OdbConnectNCrud {
    private EntityManagerFactory emf;
    private EntityManager em;
    private final String urlDb;

    public OdbConnectNCrud(String urlDb) {
        this.urlDb = urlDb;
        emf = null;
        em = null;
     }
    
    
    
    public boolean getEntityManagerAndBeginTransaction(){
        try {
        emf = Persistence.createEntityManagerFactory(urlDb);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return true;
        }
        catch (Exception e ) {System.out.println("Imposible guardar");
        return false;}
    }
    
    public boolean commitAndCloseEntityManager(){
        try {
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }
        catch(Exception e) {
            System.out.println("Imposible Cerrar");
            return false;
        }
    }
    
    public void saveObject(Object objeto){
        em.persist(objeto);
    }
    
    public List<Producto> getProductos(){
        TypedQuery<Producto> query =
                em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }
    
    public List<Cliente> getClientes(){
        TypedQuery<Cliente> query =
                em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }
    
    public List<Vendedor> getVendedores(){
        TypedQuery<Vendedor> query =
                em.createQuery("SELECT v FROM Vendedor v", Vendedor.class);
        return query.getResultList();
    }
    
    public List<Venta> getVentas(){
        TypedQuery<Venta> query =
                em.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }
    
}
