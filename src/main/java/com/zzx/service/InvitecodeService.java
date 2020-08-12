package com.zzx.service;

import com.zzx.model.Invitecode;

import java.util.List;

public interface InvitecodeService {
    /**
     * 查询所有邀请码
     *
     * @return
     */
    List<Invitecode> findAllInvitecode();

    /**
     * 保存邀请码
     */
    void save(Invitecode code);

    /**
     * 删除邀请码
     */
    void deleteInvitecode(String icode);
}
