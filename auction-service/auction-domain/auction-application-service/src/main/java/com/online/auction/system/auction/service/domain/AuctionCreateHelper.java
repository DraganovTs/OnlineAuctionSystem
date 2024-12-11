package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.mapper.AuctionDataMapper;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.PaymentRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.AuctionDomainService;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.entity.Payment;
import com.online.auction.system.auction.system.domain.entity.User;
import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class AuctionCreateHelper {
    private final AuctionDomainService auctionDomainService;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final AuctionDataMapper auctionDataMapper;

    public AuctionCreateHelper(AuctionDomainService auctionDomainService, AuctionRepository auctionRepository,
                               UserRepository userRepository, PaymentRepository paymentRepository,
                               AuctionDataMapper auctionDataMapper) {
        this.auctionDomainService = auctionDomainService;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.auctionDataMapper = auctionDataMapper;
    }


    @Transactional
    public AuctionCreatedEvent persistAuction(CreateAuctionCommand createAuctionCommand) {
        checkUser(createAuctionCommand.getUserId());
        Payment payment = checkPayment(createAuctionCommand.getPaymentId());
        Auction auction = auctionDataMapper.createAuctionCommandToAuction(createAuctionCommand, payment.getId().getValue());
        AuctionCreatedEvent auctionCreatedEvent = auctionDomainService.validateAndInitiateAuction(auction);
        saveAuction(auction);
        log.info("Auction is created whit id: {}", auctionCreatedEvent.getAuction().getId().getValue());
        return auctionCreatedEvent;
    }


    private Payment checkPayment(UUID paymentId) {

        //TODO create logic for initial payment
        return Payment.Builder.builder()
                .paymentId(new PaymentId(UUID.randomUUID()))
                .completed(true)
                .build();
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
