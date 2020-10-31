package ling.yuze.controller.customer;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.IdentityManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */

@Named
@RequestScoped
public class AddCustomer {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    
    @ManagedProperty(value="#{identityManagedBean}")
    private IdentityManagedBean identityManagedBean;
    
    private Customer customer;
    
    @PostConstruct
    public void init() {       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        
        customer = new Customer();
        
        Appuser currentUser = identityManagedBean.getCurrentUser();
        customer.setUid(currentUser);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public String createCustomer() {
        try {
            customerManagedBean.createCustomer(customer);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been created successfully"));
            return "/faces/normal/allCustomers?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to create new customer")); 
        }
        
        return null;
    }
    
}
