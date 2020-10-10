/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ling.yuze.repository.UserRepository;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class UserManagedBean implements Serializable{
    @EJB
    UserRepository userRepository;
    
    public UserManagedBean() {}
    
    public List<Appuser> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
