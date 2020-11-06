package ling.yuze.managedbean;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
    private UserRepository userRepository;
    
    public void createUser(Appuser user) throws Exception {
        userRepository.createUser(user);
    }
    
    public void editUser(Appuser user) throws Exception{
        userRepository.updateUser(user);
    }
    
    public void deleteUser (Appuser user) {
        try {
            userRepository.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Appuser getUserById(Integer id) {
        try {
            Appuser user = userRepository.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Appuser getUserByEmail(String email) {
        try {
            Appuser user = userRepository.getUserByEmail(email);
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
    
    public List<Appuser> getUsersByGender(Character gender) {
        try {
            return userRepository.searchByGender(gender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
