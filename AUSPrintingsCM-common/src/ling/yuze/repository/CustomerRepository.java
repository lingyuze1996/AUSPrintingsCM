/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.List;
import javax.ejb.Remote;
import ling.yuze.repository.entity.Contact;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */
@Remote
public interface CustomerRepository {
    void createCustomer(Customer customer) throws Exception;
    void updateCustomer(Customer customer) throws Exception;
    void deleteCustomer(Customer customer) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    Customer getCustomerById(Integer customerId) throws Exception;
    List<Customer> getCustomersByUserId(Integer userId) throws Exception;
    
    void createContact(Contact contact) throws Exception;
    void updateContact(Contact contact) throws Exception;
    void deleteContact(Contact contact) throws Exception;
    Contact getContactById(Integer contactId) throws Exception;
    
    void createIndustry(Industrytype industry) throws Exception;
    void updateIndustry(Industrytype industry) throws Exception;
    void deleteIndustry(Industrytype industry) throws Exception;
    Industrytype getIndustryById(String industryId) throws Exception;
    List<Industrytype> getAllIndustries() throws Exception;

    public List<Customer> searchCustomersByIndustryAndState(String industry, String state) throws Exception;
}
