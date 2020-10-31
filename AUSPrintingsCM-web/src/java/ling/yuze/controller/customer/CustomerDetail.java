package ling.yuze.controller.customer;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;

/**
 *
 * @author Roger
 */
@Named
@RequestScoped
public class CustomerDetail {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Customer customer;
    private Integer customerId;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public void getCustomerById() {
        customer = customerManagedBean.getCustomerById(customerId);
    }
    
    public String editCustomerById() {
        return "/faces/normal/editCustomer?faces-redirect=true&id=" + customerId;
    }
    
    
}
