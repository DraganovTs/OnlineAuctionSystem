package com.online.auction.system.autction.application.controller;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import com.online.auction.system.auction.service.domain.ports.input.service.AuctionApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/auction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuctionController {

    private final AuctionApplicationService auctionApplicationService;

    public AuctionController(AuctionApplicationService auctionApplicationService) {
        this.auctionApplicationService = auctionApplicationService;
    }

    @PostMapping("create-auction")
    public ResponseEntity<CreateAuctionResponse> createAuction(@RequestBody CreateAuctionCommand createAuctionCommand) {
        log.info("Create auction for customer: {} ", createAuctionCommand.getUserId());
        CreateAuctionResponse createAuctionResponse = auctionApplicationService.createAuction(createAuctionCommand);
        log.info("Auction created whit status: {}", createAuctionResponse.getAuctionStatus());
        return ResponseEntity.ok(createAuctionResponse);
    }

    @PostMapping("/place-bid")
    public ResponseEntity<PlaceAuctionBidResponse> placeAuctionBid(@RequestBody PlaceAuctionBidCommand placeAuctionBidCommand) {
        log.info("Place auction bid for customer: {} ", placeAuctionBidCommand.getUserId());
        PlaceAuctionBidResponse placeAuctionBidResponse = auctionApplicationService.placeAuctionBid(placeAuctionBidCommand);
        log.info("Place auction bid whit status: {}",placeAuctionBidResponse.getAuctionStatus());
        return ResponseEntity.ok(placeAuctionBidResponse);
    }
}
