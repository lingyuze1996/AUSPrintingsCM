package ling.yuze.controller.customer;

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
public class ContactDetail {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Contact contact;
    private Integer contactId;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    
    public void getContactById() {
        contact = customerManagedBean.getContactById(contactId);
    }
    
    public String editContactById() {
        return "/faces/normal/editContact?faces-redirect=true&id=" + contactId;
    }
    
    
}
