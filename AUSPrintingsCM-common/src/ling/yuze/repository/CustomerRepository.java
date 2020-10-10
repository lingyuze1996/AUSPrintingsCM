/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.Collection;
import javax.ejb.Remote;
import ling.yuze.repository.entity.Appuser;
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
    Collection<Customer> getAllCustomers() throws Exception;
    Collection<Customer> getCustomersByUser(Appuser user) throws Exception;
    
    void createIndustry(Industrytype industry) throws Exception;
    void updateIndustry(Industrytype industry) throws Exception;
    void deleteIndustry(Industrytype industry) throws Exception;
    Collection<Industrytype> getAllIndustries() throws Exception;
}
