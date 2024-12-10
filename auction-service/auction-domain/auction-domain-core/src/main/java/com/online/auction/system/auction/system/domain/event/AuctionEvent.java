package com.online.auction.system.auction.system.domain.event;

import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.common.domain.event.DomainEvent;

import java.time.LocalDateTime;

public abstract class AuctionEvent implements DomainEvent<Auction> {

    private final Auction auction;
    private final LocalDateTime createdAt;

    public AuctionEvent(Auction auction, LocalDateTime createdAt) {
        this.auction = auction;
        this.createdAt = createdAt;
    }

    public Auction getAuction() {
        return auction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
