package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public boolean checkUserById(Long id) {
        return userDao.checkUserById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }


}
