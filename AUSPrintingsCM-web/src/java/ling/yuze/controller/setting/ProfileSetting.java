package ling.yuze.controller.setting;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.IdentityManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.utility.Encryption;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class ProfileSetting implements Serializable {
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;    
    
    @ManagedProperty(value="#{IdentityManagedBean}")
    private IdentityManagedBean identityManagedBean;
    
    private Appuser currentUser;
    private String newPassword;
    private String newPasswordCheck;
    private Boolean showPasswordForm;
    
    @PostConstruct
    public void init() {       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");
     
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        currentUser = identityManagedBean.getCurrentUser(); 
        showPasswordForm = false;
    }

    public Appuser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Appuser currentUser) {
        this.currentUser = currentUser;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    public Boolean getShowPasswordForm() {
        return showPasswordForm;
    }

    public void setShowPasswordForm(Boolean showPasswordForm) {
        this.showPasswordForm = showPasswordForm;
    }
    
    public void hideForm() {
        showPasswordForm = false;
    }
    
    public void showForm() {
        showPasswordForm = true;
    }
    
    public void changePassword() {
        if (newPassword == null || newPassword.isEmpty() || newPasswordCheck == null || newPasswordCheck.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password Entry Empty! Please try again!")); 
            return;
        }
        
        if (!newPassword.equals(newPasswordCheck)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password not match! Please try again!")); 
            return;
        }
        
        String pwHash = Encryption.toSHA256(newPassword);
        currentUser.setUpassword(pwHash);
        try {
            userManagedBean.editUser(currentUser);
            hideForm();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password updated successfully!")); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to change password!")); 
        }
    }
    
    public String logOut() {
        identityManagedBean.invalidate();
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
