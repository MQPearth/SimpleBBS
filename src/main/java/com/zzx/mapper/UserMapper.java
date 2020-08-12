package com.zzx.mapper;

import com.zzx.model.User;

import java.util.List;

public interface UserMapper {

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 根据用户名查询用户
     *
     * @param user
     * @return
     */
    User findUserByUname(User user);


    /**
     * 根据用户名和密码查询用户
     *
     * @param user
     * @return
     */
    User findUserByUnameAndUpwd(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 更新用户状态
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * uid查询用户
     */
    User findUserByUid(Integer uid);


    /**
     * 更改用户密码
     */
    void updateUserPwd(User user);
}
