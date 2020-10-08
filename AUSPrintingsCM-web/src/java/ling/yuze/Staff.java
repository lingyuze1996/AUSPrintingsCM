/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
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
public class Staff implements Serializable {
    @Inject
    Principal currentUser;
    
    @Inject
    HttpSession session;
    
    public String getUsername() {
        String username = currentUser.getName();
        return username;
    }
    
    public boolean isVacant() {
        return getUsername().equals("ANONYMOUS");
    }
    
    public String logOut() {
        session.invalidate();
        return "/faces/index.xhtml";
    }
}
