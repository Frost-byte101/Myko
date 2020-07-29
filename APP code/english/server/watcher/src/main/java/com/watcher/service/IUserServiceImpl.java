package com.watcher.service;

import com.watcher.dao.IUserDao;
import com.watcher.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IUserServiceImpl implements IUserService{

    @Autowired
    private IUserDao iUserDao;

    @Override
    public String signUp(User user) {

        if("".equals(user.getUsername())){
            return "Username is empty";
        }
        if("".equals(user.getPassword())){
            return "Password is empty";
        }
        if(!"stu".equals(user.getIdentity())&&!"tea".equals(user.getIdentity())){
            return "Illegal status";
        }
        if(user.getPassword().length()<8){
            return "Password length is too short";
        }


        int i = iUserDao.signUp(user);

        if(i==1){
            return "Registration success";
        }else {
            return "Has been registered";
        }

    }

    @Override
    public String signIn(User user) {
        if("".equals(user.getUsername())){
            return "Username is empty";
        }
        if("".equals(user.getPassword())){
            return "Password is empty";
        }

        User user1 = iUserDao.signIn(user);
        if(user1==null){
            return "Login failed";
        }else {
            return user1.toJson();
        }

    }

    @Override
    public String leave(String stuid) {

        int i = iUserDao.leave(stuid);
        if(i==1){
            return "Left successfully";
        }else {
            return "Failed to leave";
        }


    }

    @Override
    public String back(String stuid) {
        int i = iUserDao.back(stuid);
        if(i==1){
            return "Return success";
        }else {
            return "Return failed";
        }
    }
}
