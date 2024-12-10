package com.online.auction.system.auction.service.domain.ports.input.service;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import jakarta.validation.Valid;

public interface AuctionApplicationService {

   CreateAuctionResponse createAuction(@Valid CreateAuctionCommand createAuctionCommand);

   PlaceAuctionBidResponse placeAuctionBid(@Valid PlaceAuctionBidCommand placeAuctionBidCommand);
}
