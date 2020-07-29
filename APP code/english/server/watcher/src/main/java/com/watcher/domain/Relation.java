package com.watcher.domain;

import com.watcher.utils.IJson;

public class Relation implements IJson {

    private Integer id;
    private Integer teaid;
    private Integer stuid;

    public Relation(Integer id, Integer teaid, Integer stuid) {
        this.id = id;
        this.teaid = teaid;
        this.stuid = stuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeaid() {
        return teaid;
    }

    public void setTeaid(Integer teaid) {
        this.teaid = teaid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", teaid=" + teaid +
                ", stuid=" + stuid +
                '}';
    }

    @Override
    public String toJson() {
        return trans(toString());
    }
}
