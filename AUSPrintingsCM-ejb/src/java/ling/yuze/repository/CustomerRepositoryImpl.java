package ling.yuze.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */
@Stateless
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext(unitName = "AUSPrintingsCM-ejbPU")
    private EntityManager em;

    @Override
    public void createCustomer(Customer customer) throws Exception {
        em.persist(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        em.merge(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) throws Exception {
        if (!em.contains(customer))
            customer = em.merge(customer);
        em.remove(customer);
    }
    
    
    @Override
    public Customer getCustomerById(Integer id) throws Exception {
        Customer customer = em.find(Customer.class, id);
        return customer;
    }
    
    @Override
    public List<Customer> getCustomersByUserId(Integer userId) throws Exception {
        Query q = em.createNamedQuery("Customer.findByUserid");
        q.setParameter("uid", userId);
        List<Customer> result = q.getResultList();
        return result;
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }
}
