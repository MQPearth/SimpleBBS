package com.zzx.controller;


import com.zzx.model.Post;

import com.zzx.model.Reply;
import com.zzx.model.User;
import com.zzx.service.PostService;
import com.zzx.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/sendPost.do", method = RequestMethod.POST)
    @ResponseBody
    public String sendPost(HttpSession session, Post post) {
        User user = (User)session.getAttribute("user");
        if (null != user) {
            if (user.getUstate() == 0)
                return "你已被禁言";
            if ((post.getPtitle().length() > 0 && post.getPtitle().length() <= 30) && (post.getPbody().length() > 0 && post.getPbody().length() < 1000)) {
                post.setPbody(post.getPbody().replaceAll("\n", "<br />"));
                post.setUser(user);
                Date date = new Date();
                post.setPsendtime(date);
                post.setLastreplytime(date);
                postService.save(post);
                return "发送成功";
            } else
                return "注意字数";
        } else
            return "未登录";
    }

//    @RequestMapping(value = "/post/{pid}.html", method = RequestMethod.GET)
//    public String postDetail(@PathVariable Long pid, Model model) {
//
//        //根据pid查询帖子
//        model.addAttribute("post", postService.findPostByPid(pid));
//
//        //根据pid分页查询回复
//        Map<String,Long> map = new HashMap<>();
//        map.put("pid",pid);
//        model.addAttribute("page", replyService.findReplyByPidAndPage(map));
//        return "post";
//    }

    @RequestMapping(value = {"/post/{pid}.html"}, method = RequestMethod.GET)
    public String replyPage(@PathVariable Long pid, @RequestParam(value = "page", required = false) Long page, Model model) {

        //根据pid查询帖子
        model.addAttribute("post", postService.findPostByPid(pid));

        //根据pid分页查询回复
        Map<String, Long> map = new HashMap<>();
        map.put("pid", pid);
        map.put("startPage", page == null ? 0L : page - 1);
        model.addAttribute("page", replyService.findReplyByPidAndPage(map));

        return "post";
    }

    @RequestMapping(value = "/reply.do", method = RequestMethod.POST)
    @ResponseBody
    public String reply(Reply reply, HttpSession session) {

        User user = (User)session.getAttribute("user");
        if (null != user) {
            if (user.getUstate() == 0)
                return "你已被禁言";
            if (reply.getReplymessage().length() > 0 && reply.getReplymessage().length() <= 1000) {
                reply.setReplymessage(reply.getReplymessage().replaceAll("\n", "<br />"));
                Date date = new Date();
                reply.setUser(user);
                reply.setReplytime(date);
                reply.getPost().setLastreplytime(date);
                replyService.saveReply(reply);
                return "回帖成功";
            } else
                return "注意字数";
        } else
            return "未登录";
    }

    @RequestMapping(value = "/delete/{pid}")
    public String deletePost(@PathVariable Long pid, HttpSession session) {

        User user = (User)session.getAttribute("user");
        Post post = postService.findPostByPid(pid);
        if ((null != user && user.getLevel() == 0) || user.getUid() == post.getUser().getUid()) {
            if (user.getUstate() != 0) //禁言状态不能删除帖子
                postService.deletePost(pid);
        }
        return "redirect:/";
    }


}
