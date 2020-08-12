package com.zzx.model;

import java.util.Date;
import java.util.List;

public class User {
    private Integer uid;
    private String uname;
    private String upwd;
    private Integer ustate;
    private Date ucreatetime;
    /**
     * 0管理员 1普通用户
     */
    private Integer level;
    private List<Post> postList;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public Date getUcreatetime() {
        return ucreatetime;
    }

    public void setUcreatetime(Date ucreatetime) {
        this.ucreatetime = ucreatetime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", ustate=" + ustate +
                ", ucreatetime=" + ucreatetime +
                ", level=" + level +
                '}';
    }
}
