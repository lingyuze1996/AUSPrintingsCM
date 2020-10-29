package ling.yuze.controller.setting;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.IdentityManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;

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
    
    @PostConstruct
    public void init() {       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        identityManagedBean = (IdentityManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "identityManagedBean");
     
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        String username = identityManagedBean.getUsername();
        currentUser = userManagedBean.getUserByEmail(username);               
    }

    public Appuser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Appuser currentUser) {
        this.currentUser = currentUser;
    }
    
    public String logOut() {
        identityManagedBean.invalidate();
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
