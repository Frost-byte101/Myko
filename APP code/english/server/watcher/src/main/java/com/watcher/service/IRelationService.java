package com.watcher.service;

import com.watcher.domain.Relation;
import com.watcher.domain.User;

import java.util.List;

public interface IRelationService {

    String getRelations(User user);

    String insertRelation(Relation relation);

    String closeRelation(Relation relation);

    String getAllStu();

    String getAllLink(String teaid);
}
