package com.watcher.service;

import com.watcher.domain.User;

public interface IUserService {

    String signUp(User user);

    String signIn(User user);

    String leave(String stuid);

    String back(String stuid);
}
