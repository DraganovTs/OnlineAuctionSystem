package com.online.auction.system.auction.system.domain.event;

import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.common.domain.event.DomainEvent;

import java.time.LocalDateTime;

public class AuctionPaymentProcessedEvent extends AuctionEvent {

    public AuctionPaymentProcessedEvent(Auction auction, LocalDateTime createdAt) {
        super(auction, createdAt);
    }
}
