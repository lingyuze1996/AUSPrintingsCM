/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ling.yuze.repository.UserRepository;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */
@Named
@RequestScoped
public class UserManagedBean implements Serializable{
    @EJB
    private UserRepository userRepository;
    private Appuser appuser = new Appuser();
    
    public Appuser getAppuser() { return appuser; }
    public void setAppuser(Appuser user) { appuser = user; }
    
    public String createUser() {
        try {
            userRepository.createUser(appuser);
            return "/faces/admin/allStaff.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Appuser getUserByEmail(String email) {
        try {
            Appuser user = userRepository.getUserByEmail(email);
            if (user != null)
                return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Appuser> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
