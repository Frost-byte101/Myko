package com.watcher.controller;

import com.watcher.domain.Task;
import com.watcher.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("task")
public class TaskC {

    @Autowired
    private ITaskService iTaskService;

    //添加任务
    @RequestMapping(value = "/insert", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String insert(@RequestBody Task task){
        return iTaskService.insertTask(task);
    }


    //获取任务
    @RequestMapping(value = "/get", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String get(@RequestParam String stuid){
        return iTaskService.getTaskByStuid(stuid);
    }

    //删除任务
    @RequestMapping(value = "/del", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String del(@RequestParam String id){
        return iTaskService.delTask(id);
    }


}
