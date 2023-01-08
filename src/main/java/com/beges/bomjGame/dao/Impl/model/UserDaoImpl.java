package com.beges.bomjGame.dao.Impl.model;

import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean checkUserById(Long id) {
        Optional<User> us = Optional.of(em.find(User.class, id));
        return us.isPresent();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }


}
