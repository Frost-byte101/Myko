package com.watcher.dao;

import com.watcher.controller.Link;
import com.watcher.domain.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITaskDao {

    List<Task> getTaskByStuid(@Param("stuid") String stuid);
    int insertTask(Task task);

    int delTask(@Param("id") String id);
}
