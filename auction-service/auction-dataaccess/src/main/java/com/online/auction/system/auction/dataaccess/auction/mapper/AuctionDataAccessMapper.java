package com.online.auction.system.auction.dataaccess.auction.mapper;

import com.online.auction.system.auction.dataaccess.auction.entity.AuctionEntity;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.common.domain.valueobject.AuctionId;
import com.online.auction.system.common.domain.valueobject.Money;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import com.online.auction.system.common.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class AuctionDataAccessMapper {

    public AuctionEntity auctionToAuctionEntity(Auction auction) {
        AuctionEntity auctionEntity = AuctionEntity.builder()
                .id(auction.getId().getValue())
                .paymentId(auction.getPaymentId().getValue())
                .userId(auction.getUserId().getValue())
                .title(auction.getTitle())
                .description(auction.getDescription())
                .startPrice(auction.getStartPrice().getAmount())
                .startTime(auction.getStartTime())
                .auctionStatus(auction.getAuctionStatus())
                .highestBid(auction.getHighestBid().getAmount())
                .endTime(auction.getEndTime())
                .failureMessages(auction.getFailureMessages() != null ?
                        String.join(",", auction.getFailureMessages()) : "")
                .build();
        return auctionEntity;
    }

    public Auction auctionEntityToAuction(AuctionEntity auctionEntity) {
        Auction auction = Auction.builder()
                .auctionId(new AuctionId(auctionEntity.getId()))
                .paymentId(new PaymentId(auctionEntity.getId()))
                .userId(new UserId(auctionEntity.getUserId()))
                .title(auctionEntity.getTitle())
                .description(auctionEntity.getDescription())
                .startPrice(new Money(auctionEntity.getStartPrice()))
                .startTime(auctionEntity.getStartTime())
                .auctionStatus(auctionEntity.getAuctionStatus())
                .highestBid(new Money(auctionEntity.getHighestBid()))
                .endTime(auctionEntity.getEndTime())
                .FailureMessages(auctionEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(auctionEntity.getFailureMessages().split(","))))
                .build();
        return auction;
    }
}
