package com.watcher.dao;

import com.watcher.domain.Relation;
import com.watcher.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRelationDao {

    List<User> getRelations(User user);

    int insertRelation(Relation relation);

    int closeRelation(Relation relation);

    List<User> getAllStu();

    List<User> getAllLink(@Param("teaid") String teaid);
}
