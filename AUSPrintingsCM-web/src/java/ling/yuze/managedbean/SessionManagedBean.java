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
public class SessionManagedBean implements Serializable {
    @Inject
    Principal currentUser;
    
    @Inject
    HttpSession session;
    
    public String getUsername() {
        String username = currentUser.getName();
        return username;
    }
    
    public boolean isAnonymous() {
        return getUsername().equals("ANONYMOUS");
    }
    
    public String logOut() {
        session.invalidate();
        return "/faces/index.xhtml";
    }
}
