package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import com.online.auction.system.auction.service.domain.mapper.AuctionDataMapper;
import com.online.auction.system.auction.service.domain.ports.output.message.publisher.payment.BidCreatedPaymentRequestMessagePublisher;
import com.online.auction.system.auction.system.domain.event.AuctionBidPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuctionPlaceBidCommandHandler {

    private final AuctionPlaceBidHelper auctionPlaceBidHelper;
    private final AuctionDataMapper auctionDataMapper;
    private final BidCreatedPaymentRequestMessagePublisher bidCreatedPaymentRequestMessagePublisher;

    public AuctionPlaceBidCommandHandler(AuctionPlaceBidHelper auctionPlaceBidHelper, AuctionDataMapper auctionDataMapper, BidCreatedPaymentRequestMessagePublisher bidCreatedPaymentRequestMessagePublisher) {
        this.auctionPlaceBidHelper = auctionPlaceBidHelper;
        this.auctionDataMapper = auctionDataMapper;
        this.bidCreatedPaymentRequestMessagePublisher = bidCreatedPaymentRequestMessagePublisher;
    }

    PlaceAuctionBidResponse placeBid(PlaceAuctionBidCommand placeAuctionBidCommand) {
        AuctionBidPlacedEvent auctionBidPlacedEvent = auctionPlaceBidHelper.persistAuction(placeAuctionBidCommand);
        log.info("Place bid for auction whit id: {}", auctionBidPlacedEvent.getAuction().getId());
        bidCreatedPaymentRequestMessagePublisher.publish(auctionBidPlacedEvent);
        return auctionDataMapper.auctionToPlaceAuctionBidResponse(auctionBidPlacedEvent.getAuction(),"Auction bid placed successfully");
    }
}
