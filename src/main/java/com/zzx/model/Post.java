package com.zzx.model;

import java.util.Date;

public class Post {

    private Long pid;
    private String ptitle;
    private String pbody;
    private Long replyCount;    //帖子回复数
    private Date psendtime;
    private User user;
    private Date lastreplytime;//最后回复时间

    public Date getLastreplytime() {
        return lastreplytime;
    }

    public void setLastreplytime(Date lastreplytime) {
        this.lastreplytime = lastreplytime;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPbody() {
        return pbody;
    }

    public void setPbody(String pbody) {
        this.pbody = pbody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPsendtime() {
        return psendtime;
    }

    public void setPsendtime(Date psendtime) {
        this.psendtime = psendtime;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }
}
