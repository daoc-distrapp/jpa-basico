
package holalibro;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Main {

    /**
     * Referencia:
     * 
     * Beginning Java™ EE 6 Platform with GlassFish
     * Antonio Goncalves
     * Chapter 2(p.41) Java Persistence
     * 
     * Recuerde:
     * 
     * Incluir los drivers para conectarse a la bdd
     * C:\glassfish4.1\javadb\lib\derbyclient.jar
     * 
     * Al crear la primera Entity, solicitar la creación
     * de la Unidad de Persistencia: persistence.xml, y
     * tomar nota del nombre: "HolaLibroPU"
     * 
     * Verificar que el servidor de base de datos esté corriendo:
     * Services/Databases/jdbc:derby://localhost:1527/sample
     *      click derecho: Connect
     * 
     */
    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.setAutor("JKRowling");
        libro.setTitulo("Harry Potter 1");
        
        EntityManager em  = Persistence.createEntityManagerFactory("HolaLibroPU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        
        //Prueba la necesidad de trabajar con una transacción en este contexto
        //Comente las líneas 42 y 44
        em.clear();
        
        Libro libro2 = em.find(Libro.class, 1);
        System.out.println("ID: " + libro2.getId());
        System.out.println("AUTOR: " + libro2.getAutor());
        System.out.println("TITULO: " + libro2.getTitulo());
        
        // Recupera todos los registros de Libro
        List<Libro> lista = em.createQuery("select l from Libro l", Libro.class).getResultList();
        for(Libro lib : lista) {
            System.out.println("ID: " + lib.getId());
            System.out.println("AUTOR: " + lib.getAutor());
            System.out.println("TITULO: " + lib.getTitulo());            
        }
    }
    
}
