/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.controller.customer;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.IdentityManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */
@Named
@RequestScoped
public class AllCustomers implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    
    @ManagedProperty(value="#{IdentityManagedBean}")
    private IdentityManagedBean identityManagedBean;
    
    private List<Customer> managedCustomers;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");
        
        Appuser currentUser = identityManagedBean.getCurrentUser();
        managedCustomers = customerManagedBean.getCustomersByUserId(currentUser.getUid());
    }

    public List<Customer> getManagedCustomers() {
        return managedCustomers;
    }

    public void setManagedCustomers(List<Customer> managedCustomers) {
        this.managedCustomers = managedCustomers;
    }
    
    public String viewCustomerById(Integer id) {
        return "/faces/normal/customer?faces-redirect=true&id=" + id;
    }
    
    public String editCustomerById(Integer id) {
        return "/faces/normal/editCustomer?faces-redirect=true&id=" + id;
    }
    
    public String deleteCustomer(Customer customer) {
        try {
            customerManagedBean.deleteCustomer(customer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been deleted successfully")); 
            
            // Refresh Customer List
            managedCustomers = customerManagedBean.getCustomersByUserId(identityManagedBean.getCurrentUser().getUid());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to delete customer")); 
        }
        
        return null;
    }
}
