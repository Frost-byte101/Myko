package com.watcher.service;


import com.watcher.dao.ITaskDao;
import com.watcher.domain.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ITaskServiceImpl implements ITaskService{

    @Autowired
    private ITaskDao iTaskDao;

    @Override
    public String getTaskByStuid(String stuid) {
        List<Task> taskByStuid = iTaskDao.getTaskByStuid(stuid);
        JSONArray jsonArray=new JSONArray();
        for (Task task : taskByStuid) {
            JSONObject jsonObject=new JSONObject(task.toJson());
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }

    @Override
    public String insertTask(Task task) {

        int i = iTaskDao.insertTask(task);

        if(i==1){
            return "Added task successfully";
        }else {
            return "Failed to add task";

        }

    }

    @Override
    public String delTask(String id) {

        int i = iTaskDao.delTask(id);

        if(i==1){
            return "Successfully deleted";
        }else {
            return "failed to delete";
        }

    }
}
