package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkUserById(Long id) {
        return userDao.checkUserById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }


}
