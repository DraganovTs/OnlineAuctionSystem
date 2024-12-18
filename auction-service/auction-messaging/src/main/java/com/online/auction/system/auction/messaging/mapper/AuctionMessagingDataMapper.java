package com.online.auction.system.auction.messaging.mapper;

import com.online.auction.kafka.model.models.PaymentMessage;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuctionMessagingDataMapper {

    public PaymentMessage auctionCreatedEventToPaymentMessage(AuctionCreatedEvent auctionCreatedEvent){
        Auction auction = auctionCreatedEvent.getAuction();
        return PaymentMessage.builder()
                .id(UUID.randomUUID().toString())
                .sagaId(UUID.randomUUID().toString())
                .auctionId(auctionCreatedEvent.getAuction().getId().toString())
                .paymentId(auctionCreatedEvent.getAuction().getPaymentId().getValue().toString())
                .customerId(auctionCreatedEvent.getAuction().getUserId().toString())
                .price(auctionCreatedEvent.getAuction().getStartPrice().getAmount())
                .createdAt(auctionCreatedEvent.getCreatedAt())
                .paymentStatus(auctionCreatedEvent.getAuction().getAuctionStatus().toString())
                .failureMessages(auctionCreatedEvent.getAuction().getFailureMessages())
                .build();
    }
}
