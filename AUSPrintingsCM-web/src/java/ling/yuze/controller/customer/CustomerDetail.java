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
import ling.yuze.repository.entity.Contact;
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
    private List<Contact> contacts;
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public void getCustomerById() {
        customer = customerManagedBean.getCustomerById(customerId);
        contacts = customer.getContactList();
    }
    
    public String deleteContact(Contact contact) {
        customerManagedBean.deleteContact(contact);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted successfully")); 
        return "/faces/normal/customer?faces-redirect=true&id=" + customerId;
    }
    
    public String editCustomerById() {
        return "/faces/normal/editCustomer?faces-redirect=true&id=" + customerId;
    }
    
    
}
