package ling.yuze.controller.customer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class AddContactAdmin implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Contact contact;
    private Integer customerId;
    
    @PostConstruct
    public void init() {
        contact = new Contact();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public String createContact() {
        try {
            Customer customer = customerManagedBean.getCustomerById(customerId);            
            contact.setCustid(customer);            
            customerManagedBean.createContact(contact);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Staff has been created successfully!"));
            return "/faces/admin/customer?faces-redirect=true&id=" + customerId;
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to create new contact!")); 
        }
        
        return null;
    }
    
}
