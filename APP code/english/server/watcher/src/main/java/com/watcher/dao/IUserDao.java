package com.watcher.dao;

import com.watcher.domain.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    int signUp(User user);

    User signIn(User user);

    int leave(@Param("stuid") String stuid);

    int back(@Param("stuid") String stuid);
}
