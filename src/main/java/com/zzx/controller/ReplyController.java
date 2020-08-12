package com.zzx.controller;


import com.zzx.model.User;
import com.zzx.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {


    @Autowired
    private ReplyService replyService;


    @RequestMapping(value = "/deleteReply/{rid}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteReply(@PathVariable Long rid, HttpSession session) {

        User user = (User)session.getAttribute("user");
        if (null != user && user.getLevel() == 0) {
            replyService.deleteReplyRid(rid);
            return "删除成功";
        }
        return "能不能删你心里没B数？";


    }
}
