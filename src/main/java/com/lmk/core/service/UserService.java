package com.lmk.core.service;

import com.lmk.core.po.User;

public interface UserService {
    public User findUser(String usercode,String password);
}
