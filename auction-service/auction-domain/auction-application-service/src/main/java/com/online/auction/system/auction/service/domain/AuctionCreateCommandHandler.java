package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.mapper.AuctionDataMapper;
import com.online.auction.system.auction.service.domain.ports.output.message.publisher.messages.AuctionCreatedMessageRequestMessagePublisher;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AuctionCreateCommandHandler {


    private final AuctionCreateHelper auctionCreateHelper;
    private final AuctionDataMapper auctionDataMapper;
    private final AuctionCreatedMessageRequestMessagePublisher auctionCreatedMessageRequestMessagePublisher;

    public AuctionCreateCommandHandler(AuctionCreateHelper auctionCreateHelper, AuctionDataMapper auctionDataMapper,
                                       AuctionCreatedMessageRequestMessagePublisher auctionCreatedMessageRequestMessagePublisher) {
        this.auctionCreateHelper = auctionCreateHelper;
        this.auctionDataMapper = auctionDataMapper;
        this.auctionCreatedMessageRequestMessagePublisher = auctionCreatedMessageRequestMessagePublisher;
    }


    public CreateAuctionResponse createAuction(CreateAuctionCommand createAuctionCommand) {
        AuctionCreatedEvent auctionCreatedEvent = auctionCreateHelper.persistAuction(createAuctionCommand);
        log.info("Auction is created whit id: {} " ,auctionCreatedEvent.getAuction().getId());
        auctionCreatedMessageRequestMessagePublisher.publish(auctionCreatedEvent);
        return auctionDataMapper.auctionToCreateAuctionResponse(auctionCreatedEvent.getAuction());
    }


}
