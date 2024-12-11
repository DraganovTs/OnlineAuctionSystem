package com.online.auction.system.auction.service.domain.mapper;


import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.common.domain.valueobject.Money;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import com.online.auction.system.common.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuctionDataMapper {

    public Auction createAuctionCommandToAuction(CreateAuctionCommand createAuctionCommand) {
        return Auction.builder()
                .userId(new UserId(createAuctionCommand.getUserId()))
                .paymentId(new PaymentId(UUID.randomUUID()))
                .title(createAuctionCommand.getTitle())
                .description(createAuctionCommand.getDescription())
                .startPrice(new Money(createAuctionCommand.getStartPrice()))
                .build();
    }

}
