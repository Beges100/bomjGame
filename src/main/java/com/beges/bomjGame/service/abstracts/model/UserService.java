package com.beges.bomjGame.service.abstracts.model;

import com.beges.bomjGame.model.User;

public interface UserService {
    boolean checkUserById(Long id);
    void save(User user);
}
