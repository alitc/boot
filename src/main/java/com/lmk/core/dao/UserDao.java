package com.lmk.core.dao;

import com.lmk.core.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User findUser(@Param("usercode") String usercode,
                         @Param("password") String password);
}
