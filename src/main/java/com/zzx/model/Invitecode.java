package com.zzx.model;

import java.util.Date;

public class Invitecode {

    private String icode;
    private Date icreatetime;
    private Integer istate;
    private User user;

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode;
    }

    public Date getIcreatetime() {
        return icreatetime;
    }

    public void setIcreatetime(Date icreatetime) {
        this.icreatetime = icreatetime;
    }

    public Integer getIstate() {
        return istate;
    }

    public void setIstate(Integer istate) {
        this.istate = istate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
