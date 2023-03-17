/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gnllsn.tpcustomer52rasoanaivoginoallison.ejb;

import com.gnllsn.tpcustomer52rasoanaivoginoallison.entities.Customer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import java.util.List;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 *
 * @author Grasoanaivo
 * on crée une "façade" pour les opérations élémentaires sur les clients : création, suppression, recherche, modification.
*/


@Stateless
public class CustomerManager {
    
    @PersistenceContext(unitName = "customerPU")
    private EntityManager em;
    
    /*va exécuter une requête JPQL dont le nom est "Customer.findAll", qui est définie dans l'entité Customer déjà écrite (la requête correspond à un "select *" sur les clients).*/
    public List<Customer> getAllCustomers() {  
        Query query = em.createNamedQuery("Customer.findAll");
       return query.getResultList();
    }  

    /*donne à gérer à l'entity manager le customer passé en paramètre (méthode merge dont les finesses seront étudiées dans le cours JPA). Au moment du commit qui aura lieu à la fin de la méthode comme vous le verrez dans le cours sur les EJB, les modifications apportées à customer depuis sa création seront enregistrées dans la base de données. Le plus souvent update génèrera un UPDATE SQL dans la base de données.*/
    public Customer update(Customer customer) {
      return em.merge(customer);  
    }  

    /*ressemble à update mais le paramètre de type Customer ne doit pas correspondre à des données déjà dans la base de données. persist génèrera un INSERT SQL dans la base de données.*/
    public void persist(Customer customer) {
       em.persist(customer);
    }    
}
