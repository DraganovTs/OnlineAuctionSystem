package com.online.auction.system.auction.system.domain;

import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.event.AuctionBidPlacedEvent;
import com.online.auction.system.auction.system.domain.event.AuctionCanceledEvent;
import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import com.online.auction.system.auction.system.domain.event.AuctionPaymentProcessedEvent;
import com.online.auction.system.common.domain.valueobject.Money;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class AuctionDomainServiceImpl implements AuctionDomainService {

    private final static String AUCTION_WHIT_ID = "Auction whit id: {} is ";

    @Override
    public AuctionCreatedEvent validateAndInitiateAuction(Auction auction) {
        auction.validateAuction();
        auction.initializeAuction();
        log.info(AUCTION_WHIT_ID + "initialized.", auction.getId().getValue());
        return new AuctionCreatedEvent(auction, LocalDateTime.now());
    }

    @Override
    public AuctionBidPlacedEvent placeBid(Auction auction, Money bid) {
        auction.placeBid(bid);
        log.info(AUCTION_WHIT_ID + "placed bid.", auction.getId().getValue());
        return new AuctionBidPlacedEvent(auction, LocalDateTime.now());
    }

    @Override
    public void approveAuction(Auction auction) {
        auction.approve();
        processAuctionPayment(auction);
        log.info(AUCTION_WHIT_ID + "approved", auction.getId().getValue());
    }

    @Override
    public AuctionCanceledEvent cancelAuctionPayment(Auction auction, List<String> failureMessages) {
        auction.initCancel(failureMessages);
        log.info(AUCTION_WHIT_ID + "cancelled", auction.getId().getValue());
        return new AuctionCanceledEvent(auction, LocalDateTime.now());
    }

    @Override
    public void cancelAuction(Auction auction, List<String> failureMessages) {
        auction.cancel(failureMessages);
        log.info(AUCTION_WHIT_ID + "cancelled.", auction.getId().getValue());
    }

    @Override
    public AuctionPaymentProcessedEvent processAuctionPayment(Auction auction) {
        log.info(AUCTION_WHIT_ID + "processed for payment", auction.getId().getValue());
        return new AuctionPaymentProcessedEvent(auction, LocalDateTime.now());
    }
}
