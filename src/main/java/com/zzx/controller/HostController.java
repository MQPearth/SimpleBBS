package com.zzx.controller;

import com.zzx.mapper.InvitecodeMapper;
import com.zzx.model.Invitecode;
import com.zzx.model.User;
import com.zzx.service.InvitecodeService;
import com.zzx.service.UserService;
import com.zzx.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class HostController {

    @Autowired
    private InvitecodeService invitecodeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/host.do")
    public String host(Model model, HttpSession session) {

        User user = (User)session.getAttribute("user");
        if (null != user && user.getLevel() == 0) {

            //查询所有邀请码
            model.addAttribute("inviteList", invitecodeService.findAllInvitecode());
            //查询所有用户
            model.addAttribute("userList", userService.findAllUser());

            return "invite";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/generateCode.do")
    @ResponseBody
    public Invitecode generateCode(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (null == user || user.getLevel() == 1)
            return null;
        Invitecode code = new Invitecode();
        code.setIcode(UUIDUtils.generateUUID().toUpperCase());
        code.setIstate(0);
        code.setIcreatetime(new Date());

        //存入数据库
        invitecodeService.save(code);
        return code;
    }

    @RequestMapping(value = "/deleteCode/{icode}")
    public String deleteCode(@PathVariable String icode, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (null == user || user.getLevel() == 1)
            return "redirect:/";

        invitecodeService.deleteInvitecode(icode);
        return "redirect:/host.do";
    }


}
