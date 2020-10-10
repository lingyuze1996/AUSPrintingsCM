/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.List;
import javax.ejb.Remote;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */
@Remote
public interface UserRepository {
    void createUser(Appuser user) throws Exception;
    void updateUser(Appuser user) throws Exception;
    void deleteUser(Appuser user) throws Exception;
    
    Appuser getUserByEmail(String email) throws Exception;
    List<Appuser> getAllUsers() throws Exception;
    //void changeUserPassword(Appuser user, String newPassword) throws Exception;
}
