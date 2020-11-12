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
    private Character gender;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        allStaff = userManagedBean.getAllUsers();
        gender = 'A';
    }

    public List<Appuser> getAllStaff() {
        return allStaff;
    }

    public void setAllStaff(List<Appuser> allStaff) {
        this.allStaff = allStaff;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Staff has been deleted successfully!")); 
            
            // Refresh Staff List
            filterByGender();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to delete staff! Please reassign customers managed by this staff first!")); 
        }
        
        return null;
    }
    
    public String filterByGender() {
        if (gender.equals('A'))
            allStaff = userManagedBean.getAllUsers();
        else
            allStaff = userManagedBean.getUsersByGender(gender);
        return null;
    }
}
