package com.online.auction.system.auction.service.domain.ports.input.message.listener.user;

import com.online.auction.system.auction.service.domain.dto.message.UserApprovedResponse;

public interface UserApprovalResponseMessageListener {

    void userApprove(UserApprovedResponse userApprovedResponse);

    void userRejected(UserApprovedResponse userApprovedResponse);
}
