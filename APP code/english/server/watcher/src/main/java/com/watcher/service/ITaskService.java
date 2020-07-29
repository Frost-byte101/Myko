package com.watcher.service;

import com.watcher.domain.Task;

public interface ITaskService {

    String getTaskByStuid(String stuid);
    String insertTask(Task task);

    String delTask(String id);
}
