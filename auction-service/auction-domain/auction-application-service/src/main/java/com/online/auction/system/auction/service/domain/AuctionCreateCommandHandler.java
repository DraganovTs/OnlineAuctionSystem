package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.mapper.AuctionDataMapper;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.PaymentRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.AuctionDomainService;
import com.online.auction.system.auction.system.domain.entity.Payment;
import com.online.auction.system.auction.system.domain.entity.User;
import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AuctionCreateCommandHandler {

    private final AuctionDomainService auctionDomainService;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final AuctionDataMapper auctionDataMapper;

    public AuctionCreateCommandHandler(AuctionDomainService auctionDomainService,
                                       AuctionRepository auctionRepository, UserRepository userRepository,
                                       PaymentRepository paymentRepository, AuctionDataMapper auctionDataMapper) {
        this.auctionDomainService = auctionDomainService;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.auctionDataMapper = auctionDataMapper;
    }

    @Transactional
    public CreateAuctionResponse createAuction(CreateAuctionCommand createAuctionCommand) {
        checkUser(createAuctionCommand.getUserId());
        Payment payment = checkPayment(createAuctionCommand.getPaymentId());
        return null;
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
}
