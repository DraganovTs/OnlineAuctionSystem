package com.online.auction.system.auction.service.domain.mapper;


import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.common.domain.valueobject.Money;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import com.online.auction.system.common.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuctionDataMapper {

    public Auction createAuctionCommandToAuction(CreateAuctionCommand createAuctionCommand, UUID paymentId) {
        return Auction.builder()
                .userId(new UserId(createAuctionCommand.getUserId()))
                .paymentId(new PaymentId(paymentId))
                .title(createAuctionCommand.getTitle())
                .description(createAuctionCommand.getDescription())
                .startPrice(new Money(createAuctionCommand.getStartPrice()))
                .build();
    }

    public CreateAuctionResponse auctionToCreateAuctionResponse(Auction auction,String message) {
        return CreateAuctionResponse.builder()
                .auctionStatus(auction.getAuctionStatus())
                .message(message)
                .build();
    }


    public PlaceAuctionBidResponse auctionToPlaceAuctionBidResponse(Auction auction) {
        return PlaceAuctionBidResponse.builder()
                .title(auction.getTitle())
                .message("Place auction bid successfully")
                .failureMessages(auction.getFailureMessages())
                .build();
    }
}
