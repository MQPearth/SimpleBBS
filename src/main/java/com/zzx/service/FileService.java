package com.zzx.service;

import com.zzx.model.File;

import java.util.List;

public interface FileService {


    /**
     * 查找该用户上传的所有文件
     *
     * @param uid
     * @return
     */
    List<File> findFileByUid(Integer uid);

    /**
     * 保存文件上传信息
     *
     * @param file
     */
    void saveFileInfo(File file);


    /**
     * 根据id查询
     *
     * @param fileId
     * @return
     */
    File findFileById(Integer fileId);

    /**
     * 删除
     *
     * @param fileId
     */
    void delete(Integer fileId);

    /**
     * 根据path查询
     *
     * @param path
     * @return
     */
    File findFileByPath(String path);

    /**
     * 获得该用户剩余容量
     *
     * @param uid
     * @return
     */
    Long getAvailableSizeByUid(Integer uid);
}
