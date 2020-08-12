package com.zzx.controller;


import com.zzx.config.FileUploadProperteis;
import com.zzx.config.NetdiskConfig;
import com.zzx.model.File;
import com.zzx.model.User;
import com.zzx.service.FileService;
import com.zzx.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@Controller
public class NetdiskController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileUploadProperteis fileUploadProperteis;


    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/netdisk.do", method = RequestMethod.GET)
    public String netdisk(Model model, HttpSession session) {

        User user = (User)session.getAttribute("user");
        if (user != null) {
            model.addAttribute("fileList", fileService.findFileByUid(user.getUid()));
            model.addAttribute("size", fileService.getAvailableSizeByUid(user.getUid()) * 1.0 / NetdiskConfig.GB_1);
            return "netdisk";
        } else
            return "redirect:/";
    }


    /**
     * 删除自己上传的文件
     *
     * @param fileId
     * @param session
     * @return
     */

    @RequestMapping(value = "/deletefile/{fileId}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable Integer fileId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        com.zzx.model.File file = fileService.findFileById(fileId);
        if (user != null && user.getUid() == file.getUser().getUid()) {
            fileService.delete(fileId);
            return "redirect:/netdisk.do";
        }
        return "redirect:/";
    }


    /**
     * 处理文件上传
     *
     * @param file
     * @param session
     * @return
     */
    @PostMapping(value = "/upload.do")
    public String upload(MultipartFile file, HttpSession session, Model model) {

        User user = (User)session.getAttribute("user");
        if (user == null) {  //未登录
            return "redirect:/";
        }

        if (file.getSize() > fileService.getAvailableSizeByUid(user.getUid())) {
            model.addAttribute("message", "你的剩余容量不足,充钱才能变得更强");
            return "error";
        }
        if (file.getSize() <= 0 || file.getSize() > NetdiskConfig.GB_1) //文件大小不符合范围
        {

            return "redirect:/netdisk.do";
        }

        String fileName = file.getOriginalFilename();   //获取文件名
        if (!fileName.contains("."))          //源文件无格式，避免后续处理出错
            fileName = fileName + ".unknow";

        String[] formatNames = fileName.split("\\.");//获取文件格式
        String formatName = "." + formatNames[formatNames.length - 1];
        String filePath = fileUploadProperteis.getUploadFolder();// 获取配置E:\\file


        java.io.File folder = new java.io.File(filePath);       //检测文件夹是否存在
        if (!folder.exists())
            folder.mkdirs();
        String uuid = UUIDUtils.generateUUID();
        java.io.File dest = new java.io.File(filePath + "/" + uuid + formatName);

        try {
            file.transferTo(dest);

            File fileInfo = new com.zzx.model.File();
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(fileUploadProperteis.getStaticAccessPath().replaceAll("\\*", "") + uuid + formatName);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setUploadTime(new Date());
            fileInfo.setState(1);
            fileInfo.setUser(user);
            fileService.saveFileInfo(fileInfo);

            return "redirect:/netdisk.do";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/download")
    public void download(Integer fileId, HttpServletResponse response, HttpSession session) throws IOException {

        Object obj = session.getAttribute("user");
        if (null == obj)
            response.sendRedirect("/");
        User user = (User)obj;
        try {
            File file = fileService.findFileById(fileId);
            if (file.getState() == 0 || file.getUser().getUid() != user.getUid())
                response.sendRedirect("/error");
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");

            OutputStream os = response.getOutputStream();
            java.io.File diskFile = new java.io.File(fileUploadProperteis.getUploadFolder() + "/" + file.getFilePath().split("/")[2]);
            FileInputStream fis = new FileInputStream(diskFile);
            response.setHeader("Content-Length", String.valueOf(diskFile.length()));
            byte[] buf = new byte[(int)diskFile.length()];
            fis.read(buf);
            os.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("/error");
        }
    }

}
