package com.zzx.service.Impl;

import com.zzx.exception.MessageException;
import com.zzx.mapper.InvitecodeMapper;
import com.zzx.mapper.UserMapper;

import com.zzx.model.Invitecode;
import com.zzx.model.User;
import com.zzx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InvitecodeMapper invitecodeMapper;


    @Override
    public void register(User user, Invitecode invitecode) throws MessageException {
        invitecode = invitecodeMapper.findinvitecodeByicode(invitecode);
        if (null != invitecode) {
            if (invitecode.getIstate() == 1)
                throw new MessageException("邀请码已使用");
            try {
                userMapper.save(user);
            } catch (RuntimeException e) {
                throw new MessageException("用户名已存在");
            }
            user = userMapper.findUserByUname(user);
            invitecode.setUser(user);
            invitecode.setIstate(1);
            invitecodeMapper.updateInvitecode(invitecode);
        } else
            throw new MessageException("邀请码不存在");
    }

    @Override
    public User login(User user) {
        return userMapper.findUserByUnameAndUpwd(user);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public void banUser(User user) {
        user.setUstate(0);
        userMapper.updateUser(user);
    }

    @Override
    public void unbanUser(User user) {
        user.setUstate(1);
        userMapper.updateUser(user);
    }


    @Override
    public User findUserByUid(Integer uid) {
        return userMapper.findUserByUid(uid);
    }

    @Override
    public void updatePassword(String uname, String oldPwd, String newPwd) throws MessageException {

        User user = new User();
        user.setUname(uname);
        user.setUpwd(oldPwd);
        User findUser = userMapper.findUserByUnameAndUpwd(user);

        if (null == findUser)
            throw new MessageException("原密码错误");

        user.setUpwd(newPwd);
        userMapper.updateUserPwd(user);
    }


}
