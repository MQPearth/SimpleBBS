package com.zzx.service.Impl;

import com.zzx.config.NetdiskConfig;
import com.zzx.mapper.FileMapper;
import com.zzx.model.File;
import com.zzx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private NetdiskConfig netdiskConfig;

    /**
     * 查找该用户上传的所有文件
     *
     * @param uid
     * @return
     */
    @Override
    public List<File> findFileByUid(Integer uid) {
        return fileMapper.findFileByUid(uid);
    }

    @Override
    public void saveFileInfo(File file) {
        fileMapper.saveFileInfo(file);
    }

    @Override
    public File findFileById(Integer fileId) {
        return fileMapper.findFileById(fileId);
    }

    @Override
    public void delete(Integer fileId) {
        fileMapper.delete(fileId);
    }

    @Override
    public File findFileByPath(String path) {


        return fileMapper.findFileByPath(path);
    }

    /**
     * 获得该用户剩余容量,默认每个用户2G
     *
     * @param uid
     * @return
     */
    @Override
    public Long getAvailableSizeByUid(Integer uid) {
        Long size = fileMapper.getAvailableSizeByUid(uid);
        return netdiskConfig.getSize() - (size == null ? 0 : size);
    }
}
