package ling.yuze.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ling.yuze.repository.CustomerRepository;
import ling.yuze.repository.entity.Contact;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class CustomerManagedBean implements Serializable {

    @EJB
    private CustomerRepository customerRepository;

    public void createCustomer(Customer customer) throws Exception {
        customerRepository.createCustomer(customer);
    }

    public void editCustomer(Customer customer) throws Exception {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        try {
            customerRepository.deleteCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void createContact(Contact contact) throws Exception {
        customerRepository.createContact(contact);
    }

    public void editContact(Contact contact) throws Exception {

        customerRepository.updateContact(contact);

    }

    public void deleteContact(Contact contact) {
        try {
            customerRepository.deleteContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(Integer id) {
        try {
            Customer customer = customerRepository.getCustomerById(id);
            return customer;
        } catch (Exception e) {
        }

        return null;
    }

    public Contact getContactById(Integer id) {
        try {
            Contact contact = customerRepository.getContactById(id);
            return contact;
        } catch (Exception e) {
        }

        return null;
    }

    public List<Customer> getCustomersByUserId(Integer id) {
        try {
            List<Customer> customers = customerRepository.getCustomersByUserId(id);
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
