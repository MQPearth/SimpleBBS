package com.zzx.mapper;

import com.zzx.model.Invitecode;

import java.util.List;

public interface InvitecodeMapper {


    /**
     * 根据邀请码id查询邀请码是否存在
     *
     * @param invitecode
     * @return
     */
    Invitecode findinvitecodeByicode(Invitecode invitecode);


    /**
     * 更新邀请码状态
     *
     * @param invitecode
     * @return 1 更新成功
     */
    int updateInvitecode(Invitecode invitecode);

    /**
     * 查询所有邀请码
     *
     * @return
     */
    List<Invitecode> findAllInvitecode();


    /**
     * 删除邀请码
     */
    void deleteInvitecode(String icode);

    /**
     * 生成邀请码
     */
    void saveInvitecode(Invitecode invitecode);
}
