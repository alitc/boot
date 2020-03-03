package com.lmk.core.service.impl;

import com.lmk.core.dao.UserDao;
import com.lmk.core.po.User;
import com.lmk.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUser(String usercode, String password) {
        User user=this.userDao.findUser(usercode,password);
        return user;
    }
}
