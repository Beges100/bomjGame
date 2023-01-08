package com.beges.bomjGame.dao.abstracts.model;

import com.beges.bomjGame.model.User;

public interface UserDao {
    boolean checkUserById(Long id);

    void save(User user);

    User getUserById(Long id);
}
