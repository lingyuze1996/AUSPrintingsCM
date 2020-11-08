package ling.yuze.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ling.yuze.repository.entity.Contact;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

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
        if (!em.contains(customer)) {
            customer = em.merge(customer);
        }
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

    @Override
    public Contact getContactById(Integer id) throws Exception {
        Contact contact = em.find(Contact.class, id);
        return contact;
    }

    @Override
    public void createContact(Contact contact) throws Exception {
        Customer customer = contact.getCustid();
        customer.getContactList().add(contact);
        em.merge(customer);
    }

    @Override
    public void updateContact(Contact contact) throws Exception {
        em.merge(contact);
    }

    @Override
    public void deleteContact(Contact contact) throws Exception {
        Customer customer = contact.getCustid();
        if (customer == null) {
            return;
        }

        customer.getContactList().remove(contact);
        em.merge(customer);
    }

    @Override
    public Industrytype getIndustryById(Integer industryId) throws Exception {
        return em.find(Industrytype.class, industryId);
    }

    @Override
    public List<Industrytype> getAllIndustries() throws Exception {
        return em.createNamedQuery("Industrytype.findAll").getResultList();
    }

    @Override
    public List<Customer> searchCustomersByIndustryAndState(String industry, String state) throws Exception {
        if (industry.toLowerCase().equals("all") && state.toLowerCase().equals("all")) {
            return this.getAllCustomers();
        } else if (industry.toLowerCase().equals("all")) {
            TypedQuery<Customer> q = em.createQuery(
                    "SELECT c FROM Customer c "
                    + "WHERE c.custaddress LIKE :state", Customer.class);
            q.setParameter("state", "%" + state + "%");
            return q.getResultList();
        } else if (state.toLowerCase().equals("all")) {
            TypedQuery<Customer> q = em.createQuery(
                    "SELECT c FROM Customer c "
                    + "WHERE c.iid.iname = :industry", Customer.class);
            q.setParameter("industry", industry);
            return q.getResultList();
        } else {
            TypedQuery<Customer> q = em.createQuery(
                    "SELECT c FROM Customer c "
                    + "WHERE c.iid.iname = :industry "
                    + "AND c.custaddress LIKE :state", Customer.class);
            q.setParameter("state", "%" + state + "%");
            q.setParameter("industry", industry);
            return q.getResultList();
        }
    }

    @Override
    public void deleteIndustry(Industrytype industry) throws Exception {
        if (!em.contains(industry)) {
            industry = em.merge(industry);
        }
        em.remove(industry);
    }

    @Override
    public void createIndustry(Industrytype industry) throws Exception {
        em.persist(industry);
    }

    @Override
    public void updateIndustry(Industrytype industry) throws Exception {
        em.merge(industry);
    }

    @Override
    public Industrytype getIndustryByName(String industry) throws Exception {
        Query q = em.createNamedQuery("Industrytype.findByIname");
        q.setParameter("iname", industry);
        List<Industrytype> result = q.getResultList();
        if (result.isEmpty())
            return null;
        
        return result.get(0);
    }

}
