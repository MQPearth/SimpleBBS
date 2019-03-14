package com.zzx.service.Impl;

import com.zzx.mapper.PostMapper;
import com.zzx.mapper.ReplyMapper;
import com.zzx.model.Page;
import com.zzx.model.Reply;
import com.zzx.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Reply> findReplyByPid(Long pid) {


        return replyMapper.findReplyByPid(pid);
    }

    @Override
    public void saveReply(Reply reply) {
        postMapper.updatePostLastReplyTime(reply.getPost());
        replyMapper.saveReply(reply);
    }

    @Override
    public Page<Reply> findReplyByPidAndPage(Map<String, Long> map) {
        Page<Reply> page = new Page<>();
        page.setShowCount(10);          //设置页面显示记录条数
        map.put("showPage", page.getShowCount().longValue());
        page.setCurrentPage((int)(map.get("startPage") + 1));//当前页，startPage 由controller设置
        map.replace("startPage", map.get("startPage") * page.getShowCount());//设置查询参数
        page.setModelList(replyMapper.findReplyByPidAndPage(map));//将查询结果存入page

        Integer postCount = (int)replyMapper.getReplyCountByPid(map.get("pid").longValue());//总回复数
        page.setPageTotal(postCount % page.getShowCount() == 0 ? postCount / page.getShowCount() : (postCount / page.getShowCount()) + 1);//计算分页页数
        return page;
    }

    @Override
    public void deleteReplyRid(Long rid) {
        replyMapper.deleteReplyByRid(rid);
    }
}
