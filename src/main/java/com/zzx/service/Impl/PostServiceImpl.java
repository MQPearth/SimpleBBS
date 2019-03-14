package com.zzx.service.Impl;

import com.zzx.mapper.PostMapper;
import com.zzx.mapper.ReplyMapper;
import com.zzx.model.Page;
import com.zzx.model.Post;
import com.zzx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public void save(Post post) {
        postMapper.save(post);
    }

    @Override
    public List<Post> findAllPost() {

        List<Post> postList = postMapper.findAllPost();

        for (Post post : postList) {//查询回复数
            post.setReplyCount(replyMapper.getReplyCountByPid(post.getPid()));
        }

        return postList;
    }

    @Override
    public Post findPostByPid(Long pid) {
        Post post = postMapper.findPostById(pid);
        post.setReplyCount(replyMapper.getReplyCountByPid(pid));
        return post;
    }

    @Override
    public void deletePost(Long pid) {
        replyMapper.deleteReply(pid);
        postMapper.deletePost(pid);
    }

    @Override
    public Page<Post> findPostByPage(Map<String, Long> map) {
        Page<Post> page = new Page<>();
        map.put("showPage", page.getShowCount().longValue());
        page.setCurrentPage((int)(map.get("startPage") + 1));
        map.replace("startPage", map.get("startPage") * page.getShowCount());
        page.setModelList(postMapper.findPostByPage(map));

        Integer postCount = postMapper.getPostCount();


        page.setPageTotal(postCount % page.getShowCount() == 0 ? postCount / page.getShowCount() : (postCount / page.getShowCount()) + 1);
        for (Post post : page.getModelList()) {//查询回复数
            post.setReplyCount(replyMapper.getReplyCountByPid(post.getPid()));
        }
        return page;
    }
}
