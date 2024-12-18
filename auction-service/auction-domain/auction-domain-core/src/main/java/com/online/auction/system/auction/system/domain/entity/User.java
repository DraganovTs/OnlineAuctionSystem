package com.online.auction.system.auction.system.domain.entity;

import com.online.auction.system.common.domain.entity.AggregateRoot;
import com.online.auction.system.common.domain.valueobject.UserId;

public class User extends AggregateRoot<UserId> {
    public User(UserId userId) {
        super.setId(userId);
    }

    public User() {
    }
}
