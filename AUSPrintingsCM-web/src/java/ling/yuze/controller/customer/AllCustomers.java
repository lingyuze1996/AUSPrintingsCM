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
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */
@Named
@RequestScoped
public class AllCustomers {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    
    @ManagedProperty(value="#{IdentityManagedBean}")
    private IdentityManagedBean identityManagedBean;
    
    private List<Customer> managedCustomers;
    private List<Industrytype> industries;
    private String industry;
    private String state;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");
        
        industries = customerManagedBean.getAllIndustries();
        state = "All";
        industry = "All";
        
        Appuser currentUser = identityManagedBean.getCurrentUser();
        managedCustomers = customerManagedBean.getCustomersByUserId(currentUser.getUid());
    }

    public List<Industrytype> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industrytype> industries) {
        this.industries = industries;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Customer> getManagedCustomers() {
        return managedCustomers;
    }

    public void setManagedCustomers(List<Customer> managedCustomers) {
        this.managedCustomers = managedCustomers;
    }
    
    public void filter() {
        managedCustomers = customerManagedBean.searchByIndustryAndState(industry, state);
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
