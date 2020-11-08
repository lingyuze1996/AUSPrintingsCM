package ling.yuze.controller.staff;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */

@Named
@RequestScoped
public class AddCustomerAdmin {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    
    private Customer customer;
    private String industry;
    
    private List<Industrytype> industries;
    private List<Appuser> users;
    private Integer user;
    
    @PostConstruct
    public void init() {       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        
        customer = new Customer();
        users = userManagedBean.getAllUsers();
        
        industries = customerManagedBean.getAllIndustries();        
    }

    public List<Appuser> getUsers() {
        return users;
    }

    public void setUsers(List<Appuser> users) {
        this.users = users;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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
            Appuser assigned = userManagedBean.getUserById(user);
            customer.setUid(assigned);
            
            Industrytype industryType = customerManagedBean.getIndustryByName(industry);
            customer.setIid(industryType);
            customerManagedBean.createCustomer(customer);
            return "/faces/admin/allCustomers?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to create new customer")); 
        }
        
        return null;
    }
    
}
