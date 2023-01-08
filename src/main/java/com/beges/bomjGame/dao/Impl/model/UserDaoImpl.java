package com.beges.bomjGame.dao.Impl.model;

import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean checkUserById(Long id) {
        Optional<User> user = Optional.of(entityManager.find(User.class, id));
        return user.isPresent();
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


}
