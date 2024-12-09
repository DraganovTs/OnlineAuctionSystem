package com.online.auction.system.common.domain.valueobject;

import java.util.UUID;

public class AuctionId extends BaseId<UUID> {

    protected AuctionId(UUID value) {
        super(value);
    }
}
