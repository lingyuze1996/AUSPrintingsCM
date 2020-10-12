/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        em.persist(user);
    }

    @Override
    public void updateUser(Appuser user) throws Exception {
        em.merge(user);
    }

    @Override
    public void deleteUser(Appuser user) throws Exception {
        if (!em.contains(user))
            user = em.merge(user);
        em.remove(user);
    }
    
    @Override
    public Appuser getUserById(Integer id) throws Exception {
        Appuser user = em.find(Appuser.class, id);
        return user;
    }
    
    @Override
    public Appuser getUserByEmail(String email) throws Exception {
        Query q = em.createNamedQuery("Appuser.findByUemail");
        q.setParameter("uemail", email);
        List<Appuser> result = q.getResultList();
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    @Override
    public List<Appuser> getAllUsers() throws Exception {
        return em.createNamedQuery("Appuser.findAll").getResultList();
    }
}
