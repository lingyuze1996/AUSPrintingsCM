/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ling.yuze.repository.entity.Appuser;

/**
 *
 * @author Roger
 */
@Stateless
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext(unitName = "AUSPrintingsCM-ejbPU")
    private EntityManager em;

    @Override
    public void createUser(Appuser user) throws Exception {
        
    }

    @Override
    public void updateUser(Appuser user) throws Exception {
        
    }

    @Override
    public void deleteUser(Appuser user) throws Exception {
        
    }

    @Override
    public List<Appuser> getAllUsers() throws Exception {
        return em.createNamedQuery("Appuser.findAll").getResultList();
    }
}
