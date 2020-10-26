package ling.yuze.controller.staff;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */

@Named
@RequestScoped
public class AddStaff {
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    private Appuser staff;
    
    @PostConstruct
    public void init() {
        staff = new Appuser();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public Appuser getStaff() {
        return staff;
    }

    public void setStaff(Appuser staff) {
        this.staff = staff;
    }
    
    public String createStaff() {
        try {
            userManagedBean.createUser(staff);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Staff has been created successfully"));
            return "/faces/admin/allStaff?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to create new staff")); 
        }
        
        return null;
    }
    
}
