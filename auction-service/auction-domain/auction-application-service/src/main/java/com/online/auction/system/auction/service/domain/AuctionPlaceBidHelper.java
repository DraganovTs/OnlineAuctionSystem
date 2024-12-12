package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.AuctionDomainService;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.entity.User;
import com.online.auction.system.auction.system.domain.event.AuctionBidPlacedEvent;
import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.common.domain.valueobject.Money;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AuctionPlaceBidHelper {

    private final AuctionDomainService auctionDomainService;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    public AuctionPlaceBidHelper(AuctionDomainService auctionDomainService, AuctionRepository auctionRepository, UserRepository userRepository) {
        this.auctionDomainService = auctionDomainService;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AuctionBidPlacedEvent persistAuction(PlaceAuctionBidCommand placeAuctionBidCommand) {
        checkUser(placeAuctionBidCommand.getUserId());
        Auction auction = checkAuction(placeAuctionBidCommand.getAuctionId());
        AuctionBidPlacedEvent auctionBidPlacedEvent = auctionDomainService.placeBid(auction,
                new Money(placeAuctionBidCommand.getBid()));
        saveAuction(auction);
        log.info("Auction have Placed Bid: {}", placeAuctionBidCommand.getBid());
        return auctionBidPlacedEvent;

    }

    private Auction checkAuction(UUID auctionId) {
        Optional<Auction> optionalAuction = auctionRepository.findById(auctionId);
        if (optionalAuction.isEmpty()) {
            throw new AuctionDomainException("Auction id " + auctionId + " not found");
        }
        return optionalAuction.get();
    }


    private void checkUser(UUID userId) {
        Optional<User> user = userRepository.findUser(userId);
        if (user.isEmpty()) {
            log.warn("Could not find customer with id {}", userId);
            throw new AuctionDomainException("Could not find customer with id " + userId);
        }
    }

    private Auction saveAuction(Auction auction) {
        Auction savedAuction = auctionRepository.save(auction);
        if (savedAuction == null) {
            log.error("Could not save auction whit id: {}", auction.getId().getValue());
            throw new AuctionDomainException("Could not save the Auction!");
        }
        log.info("Saved auction whit id: {}", savedAuction.getId().getValue());
        return savedAuction;
    }
}
