package com.watcher.controller;

import com.watcher.domain.User;
import com.watcher.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class Login {

    @Autowired
    private IUserService iUserService;

    //用户注册
    @RequestMapping(value = "/signup", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String signUp(@RequestBody User user){//注册接口
        return iUserService.signUp(user);
    }

    //用户登录
    @RequestMapping(value = "/signin", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String signIn(@RequestBody User user){//登录接口
        return iUserService.signIn(user);
    }

    //用户状态离开更新
    @RequestMapping(value = "/leave", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String leave(@RequestParam String stuid){//登录接口
        return iUserService.leave(stuid);
    }

    //用户状态返回更新
    @RequestMapping(value = "/back", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String back(@RequestParam String stuid){//登录接口
        return iUserService.back(stuid);
    }
}
