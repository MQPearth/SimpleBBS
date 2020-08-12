package com.zzx.controller;

import com.zzx.exception.MessageException;
import com.zzx.model.Invitecode;
import com.zzx.model.User;
import com.zzx.service.UserService;
import com.zzx.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String register(User user, Invitecode invitecode, @RequestParam(value = "yzm", required = false) String yzm,
                           HttpSession session) {
        if (user.getUname().length() > 16 || user.getUpwd().length() > 16 || user.getUpwd().length() < 6) {
            return "注册失败:用户名或密码长度必须小于16位";
        }

        if (session.getAttribute("yzm").equals(yzm.toLowerCase())) {
            user.setUpwd(DigestUtils.md5DigestAsHex(user.getUpwd().getBytes()));
            user.setLevel(1);
            user.setUcreatetime(new Date());
            user.setUstate(1);
            try {
                userService.register(user, invitecode);
                return "注册成功";
            } catch (MessageException e) {
                return e.getMessage();
            }
        } else
            return "验证码错误";

    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(User user, @RequestParam(value = "yzm", required = false) String yzm,
                        @RequestParam(value = "autoLogin", required = false) String autoFlag, HttpSession session,
                        HttpServletRequest request, HttpServletResponse response) {

        if (session.getAttribute("yzm").equals(yzm.toLowerCase())) {
            user.setUpwd(DigestUtils.md5DigestAsHex(user.getUpwd().getBytes()));

            user = userService.login(user);
            if (null != user) {
                session.setAttribute("user", user);
                Cookie c = new Cookie("JSESSIONID", session.getId());
                // session默认销毁时间30分钟
                c.setMaxAge(60 * 30);
                response.addCookie(c);
                return "登录成功";
            } else
                return "登录失败";
        } else
            return "验证码错误";
    }

    @ResponseBody
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String loginout(HttpSession session) {
        session.removeAttribute("user");
        return "退出成功";
    }

    @ResponseBody
    @RequestMapping(value = "/yzm.do", method = RequestMethod.GET)
    public void valicode(HttpServletResponse response, HttpSession session) throws Exception {

        Object[] objs = ImageUtil.createImage();
        // 将验证码存入Session
        session.setAttribute("yzm", ((String)objs[0]).toLowerCase());
        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage)objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @RequestMapping(value = "/ban/{uid}")
    @ResponseBody
    public String banUser(@PathVariable Integer uid, HttpSession session) {

        User onlineUser = (User)session.getAttribute("user");
        if (onlineUser == null || onlineUser.getLevel() == 1)
            return "没有权限";
        User user = userService.findUserByUid(uid);
        if (user.getLevel() == 0)
            return "此账号为管理员";
        userService.banUser(user);
        return "禁言成功";
    }

    @RequestMapping(value = "/unban/{uid}")
    @ResponseBody
    public String unbanUser(@PathVariable Integer uid, HttpSession session) {

        User onlineUser = (User)session.getAttribute("user");
        if (onlineUser == null || onlineUser.getLevel() == 1)
            return "没有权限";
        User user = new User();
        user.setUid(uid);
        userService.unbanUser(user);
        return "解禁成功";
    }

    @RequestMapping("/person.do")
    public String user(HttpSession session) {

        User user = (User)session.getAttribute("user");
        if (user == null)
            return "redirect:/";
        return "person";
    }

    @PostMapping("/updatePassword.do")
    public String updatePassword(Model model, HttpSession session, @RequestParam String oldPwd,
                                 @RequestParam String newPwd) {

        User user = (User)session.getAttribute("user");
        if (user == null)
            return "redirect:/";
        if (newPwd.length() <= 6 || newPwd.length() > 16) {
            model.addAttribute("message", "新密码长度(6,16]位");
            return "error";
        }
        try {
            userService.updatePassword(user.getUname(), DigestUtils.md5DigestAsHex(oldPwd.getBytes()),
                    DigestUtils.md5DigestAsHex(newPwd.getBytes()));
            session.removeAttribute("user");
        } catch (MessageException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/";

    }

}
