/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.controller.staff;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
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
public class StaffDetail {
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    private Appuser staff;
    private Integer staffId;
    
    @PostConstruct
    public void init() {
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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public void getStaffById() {
        staff = userManagedBean.getUserById(staffId);
    }
    
    public String editStaffById() {
        return "/faces/admin/editStaff?faces-redirect=true&id=" + staffId;
    }
    
    
}
