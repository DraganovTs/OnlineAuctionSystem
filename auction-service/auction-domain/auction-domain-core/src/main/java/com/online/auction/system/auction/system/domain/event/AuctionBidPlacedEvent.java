package com.online.auction.system.auction.system.domain.event;


import com.online.auction.system.auction.system.domain.entity.Auction;

import java.time.LocalDateTime;

public class AuctionBidPlacedEvent extends AuctionEvent {


    public AuctionBidPlacedEvent(Auction auction, LocalDateTime createdAt) {
        super(auction, createdAt);
    }
}
