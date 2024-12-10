package com.online.auction.system.auction.service.domain.dto.message;

import com.online.auction.system.common.domain.valueobject.UserStatus;

import java.time.Instant;
import java.util.List;

public class UserApprovedResponse {
    private String userId;
    private String auctionId;
    private Instant approvedAt;
    private UserStatus userStatus;
    private List<String > failureMessages;
}
