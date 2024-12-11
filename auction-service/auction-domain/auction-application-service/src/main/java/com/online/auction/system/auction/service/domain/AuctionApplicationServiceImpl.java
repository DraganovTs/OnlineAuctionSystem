package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import com.online.auction.system.auction.service.domain.ports.input.service.AuctionApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
class AuctionApplicationServiceImpl implements AuctionApplicationService {

    private final AuctionCreateCommandHandler auctionCreateCommandHandler;
    private final AuctionPlaceBidCommandHandler placeBidCommandHandler;

    AuctionApplicationServiceImpl(AuctionCreateCommandHandler auctionCreateCommandHandler, AuctionPlaceBidCommandHandler placeBidCommandHandler) {
        this.auctionCreateCommandHandler = auctionCreateCommandHandler;
        this.placeBidCommandHandler = placeBidCommandHandler;
    }


    @Override
    public CreateAuctionResponse createAuction(CreateAuctionCommand createAuctionCommand) {
        return auctionCreateCommandHandler.createAuction(createAuctionCommand);
    }

    @Override
    public PlaceAuctionBidResponse placeAuctionBid(PlaceAuctionBidCommand placeAuctionBidCommand) {
        return placeBidCommandHandler.placeBid(placeAuctionBidCommand);
    }
}
