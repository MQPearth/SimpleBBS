package com.zzx.model;

import java.util.Date;

public class File {
    /**
     * 文件id
     */
    private Integer fileId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 状态
     */
    private Integer state;
    private User user;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
