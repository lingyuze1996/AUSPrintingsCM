package ling.yuze.controller.customer;

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
public class AddCustomer {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    
    @ManagedProperty(value="#{identityManagedBean}")
    private IdentityManagedBean identityManagedBean;
    
    private Customer customer;
    private String industry;
    
    private List<Industrytype> industries;
    
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
        
        industries = customerManagedBean.getAllIndustries();        
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<Industrytype> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industrytype> industries) {
        this.industries = industries;
    }
    
    public String createCustomer() {
        try {
            Industrytype industryType = customerManagedBean.getIndustryByName(industry);
            customer.setIid(industryType);
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
