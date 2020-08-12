package com.zzx.service;

import com.zzx.model.Page;
import com.zzx.model.Post;

import java.util.List;
import java.util.Map;

public interface PostService {


    /**
     * 保存帖子
     *
     * @param post
     */
    void save(Post post);

    /**
     * 查询所有帖子
     *
     * @return
     */
    List<Post> findAllPost();

    /**
     * 根据帖子id查询帖子
     *
     * @param pid
     * @return
     */
    Post findPostByPid(Long pid);


    /**
     * 根据帖子id删除帖子
     *
     * @param pid
     */
    void deletePost(Long pid);


    /**
     * 分页查询帖子
     *
     * @param map
     * @return
     */
    Page<Post> findPostByPage(Map<String, Long> map);
}
