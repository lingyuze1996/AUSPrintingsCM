package ling.yuze.managedbean;

import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roger
 */

@Named
@SessionScoped
public class IdentityManagedBean implements Serializable {
    @Inject
    Principal currentUser;
    
    @Inject
    HttpSession session;
    
    private String username;
    
    public String getUsername() {
        return currentUser.getName();
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void invalidate() {
        session.invalidate();
    }
}
