package ling.yuze.controller.staff;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
public class EditStaff implements Serializable {
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
    
    public String editStaff() {
        try {
            userManagedBean.editUser(staff);
            return "/faces/admin/staff?faces-redirect=true&id=" + staffId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}
