package com.zzx.controller;


import com.zzx.service.PostService;
import com.zzx.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session, Model model, @RequestParam(value = "page", required = false) Long page) {
        Map<String, Long> map = new HashMap<>();
        map.put("startPage", page == null ? 0 : page - 1);
        model.addAttribute("page", postService.findPostByPage(map));

        return "index";
    }


}
