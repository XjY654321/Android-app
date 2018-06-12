package com.bs.demo.myapplication.bean;

/**
 * Created by zjw on 2018/3/27.
 */

public class SQInfo {
    String id;
    String ly;
    String time;
    UserInfo user;
    TDInfo td;
    String sh;

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public TDInfo getTd() {
        return td;
    }

    public void setTd(TDInfo td) {
        this.td = td;
    }
}
