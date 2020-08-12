package com.zzx.mapper;

import com.zzx.model.Post;
import com.zzx.model.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {

    /**
     * 查询帖子回复数
     *
     * @param pid
     * @return
     */
    long getReplyCountByPid(Long pid);


    /**
     * 根据帖子id查询回复
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
     * 删除帖子下的所有回复
     *
     * @param pid
     */
    void deleteReply(Long pid);


    /**
     * 分页查询帖子回复
     *
     * @param map
     * @return
     */
    List<Reply> findReplyByPidAndPage(Map<String, Long> map);


    /**
     * 根据回复id删除回复
     *
     * @param rid
     */
    void deleteReplyByRid(Long rid);

}
