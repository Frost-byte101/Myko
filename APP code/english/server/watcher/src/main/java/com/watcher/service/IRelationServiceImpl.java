package com.watcher.service;

import com.watcher.dao.IRelationDao;
import com.watcher.domain.Relation;
import com.watcher.domain.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRelationServiceImpl implements IRelationService {

    @Autowired
    private IRelationDao iRelationDao;

    @Override
    public String getRelations(User user) {
        List<User> relations = iRelationDao.getRelations(user);
        JSONArray jsonArray=new JSONArray();
        for (User relation : relations) {
            JSONObject jsonObject=new JSONObject(relation.toJson());
            jsonObject.remove("password");
            jsonObject.remove("identity");
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String insertRelation(Relation relation) {

        int i = iRelationDao.insertRelation(relation);
        if(i==1){
            return "Relationship binding successfully";
        }else {
            return "Relationship binding failed";
        }

    }

    @Override
    public String closeRelation(Relation relation) {

        int i = iRelationDao.closeRelation(relation);
        if(i==1){
            return "Successfully untie the relationship";
        }else {
            return "Has been bound by someone else";
        }

    }

    @Override
    public String getAllStu() {

        List<User> relations = iRelationDao.getAllStu();
        JSONArray jsonArray=new JSONArray();
        for (User relation : relations) {
            JSONObject jsonObject=new JSONObject(relation.toJson());
            jsonObject.remove("password");
            jsonObject.remove("identity");
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String getAllLink(String teaid) {
        List<User> allLink = iRelationDao.getAllLink(teaid);
        JSONArray jsonArray=new JSONArray();
        for (User relation : allLink) {
            JSONObject jsonObject=new JSONObject(relation.toJson());
            jsonObject.remove("password");
            jsonObject.remove("identity");
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
