/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.controller.staff;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
public class AllStaff implements Serializable {
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    private List<Appuser> allStaff;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        allStaff = userManagedBean.getAllUsers();
    }

    public List<Appuser> getAllStaff() {
        return allStaff;
    }

    public void setAllStaff(List<Appuser> allStaff) {
        this.allStaff = allStaff;
    }
    
    public String viewStaffById(Integer id) {
        return "/faces/admin/staff?faces-redirect=true&id=" + id;
    }
    
    public String editStaffById(Integer id) {
        return "/faces/admin/editStaff?faces-redirect=true&id=" + id;
    }
    
    public String deleteStaff(Appuser staff) {
        try {
            userManagedBean.deleteUser(staff);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Staff has been deleted successfully")); 
            
            // Refresh Staff List
            allStaff = userManagedBean.getAllUsers();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to delete staff")); 
        }
        
        return null;
    }
}
