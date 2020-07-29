package com.watcher.controller;

import com.watcher.dao.IRelationDao;
import com.watcher.domain.Relation;
import com.watcher.domain.User;
import com.watcher.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("link")
public class Link {

    @Autowired
    private IRelationService iRelationService;

    //用户绑定关系
    @RequestMapping(value = "/link", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String link(@RequestBody Relation relation){
        return iRelationService.insertRelation(relation);
    }

    //用户绑定解除
    @RequestMapping(value = "/closelink", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String closeLink(@RequestBody Relation relation){
        return iRelationService.closeRelation(relation);
    }

    //查询指定教师的学生
    @RequestMapping(value = "/getrelations", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String select(@RequestBody User user){
        return iRelationService.getRelations(user);
    }

    //查询所有教师
    @RequestMapping(value = "/getallstu", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String getAllTea(){
        return iRelationService.getAllStu();
    }

    //查询所有绑定关系
    @RequestMapping(value = "/getalllink", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String getAllLink(@RequestParam String teaid){
        return iRelationService.getAllLink(teaid);
    }

}
