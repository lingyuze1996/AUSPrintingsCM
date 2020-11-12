package ling.yuze.controller.customer;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.IdentityManagedBean;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class EditCustomer implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Customer customer;
    private Integer id;
    private String industry;
    private List<Industrytype> industries;

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
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        
        industries = customerManagedBean.getAllIndustries();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void getCustomerById() throws Exception {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        IdentityManagedBean identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");        
  
        customer = customerManagedBean.getCustomerById(id);
        if (!customer.getUid().getUid().equals(identityManagedBean.getCurrentUser().getUid()))
            throw new Exception();
        industry = customer.getIid().getIname();
    }
    
    public String editCustomer() {
        try {
            customer.setIid(customerManagedBean.getIndustryByName(industry));
            customerManagedBean.editCustomer(customer);
            return "/faces/normal/customer?faces-redirect=true&id=" + customer.getCustid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}
