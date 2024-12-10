package com.online.auction.system.auction.system.domain.event;

import com.online.auction.system.auction.system.domain.entity.Auction;

import java.time.LocalDateTime;

public class AuctionCancelEvent extends AuctionEvent {


    public AuctionCancelEvent(Auction auction, LocalDateTime createdAt) {
        super(auction, createdAt);
    }
}
