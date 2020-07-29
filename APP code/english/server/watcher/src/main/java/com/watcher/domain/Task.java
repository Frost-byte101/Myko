package com.watcher.domain;

import com.watcher.utils.IJson;

public class Task implements IJson {

    private Integer id;
    private Integer stuid;
    private String content;
    private String starttime;
    private String endtime;
    private String status;

    public Task(Integer id, Integer stuid, String content, String starttime, String endtime, String status) {
        this.id = id;
        this.stuid = stuid;
        this.content = content;
        this.starttime = starttime;
        this.endtime = endtime;
        this.status = status;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", stuid=" + stuid +
                ", content='" + content + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public String toJson() {
        return trans(toString());
    }
}
