package com.online.auction.system.auction.service.domain.ports.input.message.listener.user;

import com.online.auction.system.auction.service.domain.dto.message.UserApprovedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class UserApprovalResponseMessageListenerImpl implements UserApprovalResponseMessageListener {
    @Override
    public void userApprove(UserApprovedResponse userApprovedResponse) {

    }

    @Override
    public void userRejected(UserApprovedResponse userApprovedResponse) {

    }
}
