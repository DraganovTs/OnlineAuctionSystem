package com.online.auction.system.auction.system.domain;

import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.event.AuctionBidPlacedEvent;
import com.online.auction.system.auction.system.domain.event.AuctionCanceledEvent;
import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import com.online.auction.system.auction.system.domain.event.AuctionPaymentProcessedEvent;
import com.online.auction.system.common.domain.valueobject.Money;

import java.util.List;

public interface AuctionDomainService {

    AuctionCreatedEvent validateAndInitiateAuction(Auction auction);

    AuctionBidPlacedEvent placeBid(Auction auction , Money bid);

    void approveAuction(Auction auction);

    AuctionCanceledEvent cancelAuctionPayment(Auction auction, List<String> failureMessages);

    void cancelAuction(Auction auction, List<String> failureMessages);

    AuctionPaymentProcessedEvent processAuctionPayment(Auction auction);
}
