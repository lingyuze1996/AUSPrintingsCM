package ling.yuze.managedbean;

import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import ling.yuze.repository.UserRepository;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */

@Named
@SessionScoped
public class IdentityManagedBean implements Serializable {
    @Inject
    Principal principal;
    
    @Inject
    HttpSession session;
    
    @EJB
    private UserRepository userRepository;
    
    private Appuser currentUser;
    
    @PostConstruct
    public void init() {
        String username = principal.getName();
        try {
            currentUser = userRepository.getUserByEmail(username);
        } catch (Exception e) {}
    }
    
    public Appuser getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(Appuser user) {
        this.currentUser = user;
    }
    
    public void invalidate() {
        session.invalidate();
    }
}
