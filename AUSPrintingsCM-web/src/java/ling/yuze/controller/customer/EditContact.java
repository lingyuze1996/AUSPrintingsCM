package ling.yuze.controller.customer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.repository.entity.Contact;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class EditContact implements Serializable {
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
    
    public String editContact() {
        try {
            customerManagedBean.editContact(contact);
            return "/faces/normal/contact?faces-redirect=true&id=" + contactId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}
