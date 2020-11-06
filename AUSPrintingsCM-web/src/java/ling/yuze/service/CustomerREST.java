package ling.yuze.service;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ling.yuze.repository.CustomerRepository;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */
@RequestScoped
@Path("customer")
public class CustomerREST {

    @EJB
    private CustomerRepository repo;

    public CustomerREST() {
        
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Integer id) {
       try {
           return repo.getCustomerById(id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        try {
            return repo.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
