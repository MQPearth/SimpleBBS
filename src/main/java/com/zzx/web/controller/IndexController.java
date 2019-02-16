package com.zzx.web.controller;


import com.zzx.model.Page;
import com.zzx.model.Post;
import com.zzx.model.User;
import com.zzx.service.PostService;
import com.zzx.service.UserService;
import com.zzx.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session,Model model, @RequestParam(value = "page", required = false) Long page) {
        Map<String, Long> map = new HashMap<>();
        map.put("startPage", page == null ? 0 : page - 1);
        model.addAttribute("page", postService.findPostByPage(map));


//        //测试用
//        User user = new User();
//        user.setUname("admin");
//        user.setUpwd(DigestUtils.md5DigestAsHex("2".getBytes()));
//        user = userService.login(user);
//        session.setAttribute("user", user);

        return "index";
    }


}
