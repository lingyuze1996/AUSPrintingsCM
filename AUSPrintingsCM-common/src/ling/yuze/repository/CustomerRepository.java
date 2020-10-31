/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.List;
import javax.ejb.Remote;
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
    
    //void createIndustry(Industrytype industry) throws Exception;
    //void updateIndustry(Industrytype industry) throws Exception;
    //void deleteIndustry(Industrytype industry) throws Exception;
    //Collection<Industrytype> getAllIndustries() throws Exception;
}
