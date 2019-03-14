package com.zzx.service.Impl;

import com.zzx.mapper.InvitecodeMapper;
import com.zzx.model.Invitecode;
import com.zzx.service.InvitecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class InvitecodeServiceImpl implements InvitecodeService {

    @Autowired
    private InvitecodeMapper invitecodeMapper;

    @Override
    public List<Invitecode> findAllInvitecode() {
        return invitecodeMapper.findAllInvitecode();
    }

    @Override
    public void save(Invitecode code) {
        invitecodeMapper.saveInvitecode(code);
    }

    @Override
    public void deleteInvitecode(String icode) {
        invitecodeMapper.deleteInvitecode(icode);
    }
}
