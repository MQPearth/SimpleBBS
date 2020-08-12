package com.zzx.service;

import com.zzx.model.Page;
import com.zzx.model.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyService {


    /**
     * 根据帖子id查询帖子回复
     *
     * @param pid
     * @return
     */
    List<Reply> findReplyByPid(Long pid);


    /**
     * 保存回复
     *
     * @param reply
     */
    void saveReply(Reply reply);

    /**
     * 根据帖子id分页查询回复
     *
     * @return
     */
    Page<Reply> findReplyByPidAndPage(Map<String, Long> map);


    /**
     * 根据回复id删除回复
     *
     * @param rid
     */
    void deleteReplyRid(Long rid);
}
