package com.online.auction.system.auction.system.domain.event;


import com.online.auction.system.auction.system.domain.entity.Auction;

import java.time.LocalDateTime;

public class AuctionCreatedEvent extends AuctionEvent {


    public AuctionCreatedEvent(Auction auction, LocalDateTime createdAt) {
        super(auction, createdAt);
    }
}