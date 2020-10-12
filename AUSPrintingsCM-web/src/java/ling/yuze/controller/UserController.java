/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
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
@SessionScoped
public class UserController implements Serializable {
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    private Appuser user;
    
    @PostConstruct
    public void init() {
        user = new Appuser();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public Appuser getUser() { return user; }
    public void setUser(Appuser user) { this.user = user; }
    
    public String getUserDetailsById(Integer id) {
        this.user = userManagedBean.getUserById(id);
        return "/faces/admin/staff?faces-redirect=trueid=" + id;
    }
    
    public String EditUserDetailsById(Integer id) {
        this.user = userManagedBean.getUserById(id);
        return "/faces/admin/editStaff?faces-redirect=true";
    }
    
    public String editUser() {
        try {
            userManagedBean.editUser(user);
            return "/faces/admin/staff?faces-redirect=trueid=" + user.getUid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String deleteUserById(Integer id) {
        userManagedBean.deleteUserById(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Staff has been deleted successfully")); 
        return null;
    }   
}
