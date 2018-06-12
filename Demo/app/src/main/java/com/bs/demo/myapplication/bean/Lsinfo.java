package com.bs.demo.myapplication.bean;

/**
 * Created by zjw on 2018/4/6.
 */

public class Lsinfo {
    String id;
    String time;
    KCInfo kcInfo;
    String kcid;
    String sl;
    String bq;
    String name;
    String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public KCInfo getKcInfo() {
        return kcInfo;
    }

    public void setKcInfo(KCInfo kcInfo) {
        this.kcInfo = kcInfo;
    }

    public String getKcid() {
        return kcid;
    }

    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
