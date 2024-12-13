package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionCommand;
import com.online.auction.system.auction.service.domain.dto.create.CreateAuctionResponse;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidCommand;
import com.online.auction.system.auction.service.domain.dto.create.PlaceAuctionBidResponse;
import com.online.auction.system.auction.service.domain.mapper.AuctionDataMapper;
import com.online.auction.system.auction.service.domain.ports.input.service.AuctionApplicationService;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.PaymentRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.entity.Auction;
import com.online.auction.system.auction.system.domain.entity.Payment;
import com.online.auction.system.auction.system.domain.entity.User;
import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.common.domain.valueobject.AuctionId;
import com.online.auction.system.common.domain.valueobject.AuctionStatus;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import com.online.auction.system.common.domain.valueobject.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = AuctionTestConfiguration.class)
public class AuctionApplicationServiceTest {

    @Autowired
    private AuctionApplicationService auctionApplicationService;
    @Autowired
    private AuctionDataMapper auctionDataMapper;
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;

    private CreateAuctionCommand createAuctionCommand;
    private CreateAuctionCommand createAuctionCommandWrongStartingPrice;
    private PlaceAuctionBidCommand placeAuctionBidCommand;
    private PlaceAuctionBidCommand placeAuctionBidCommandWrongStartingPrice;
    private final UUID USER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID PAYMENT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb42");
    private final UUID AUCTION_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43");
    private final BigDecimal STARTING_PRICE = new BigDecimal("200");
    private final BigDecimal WRONG_STARTING_PRICE = new BigDecimal("0");

    @BeforeAll
    public void init() {
        createAuctionCommand = CreateAuctionCommand.builder()
                .userId(USER_ID)
                .paymentId(PAYMENT_ID)
                .startPrice(STARTING_PRICE)
                .title("TEST_AUCTION_TITLE")
                .description("TEST_AUCTION_DESCRIPTION")
                .build();

        createAuctionCommandWrongStartingPrice = CreateAuctionCommand.builder()
                .userId(USER_ID)
                .paymentId(PAYMENT_ID)
                .startPrice(WRONG_STARTING_PRICE)
                .title("TEST_AUCTION_TITLE")
                .description("TEST_AUCTION_DESCRIPTION")
                .build();

        User user = new User();
        user.setId(new UserId(USER_ID));

        Payment paymentResponse = Payment.builder()
                .paymentId(new PaymentId(PAYMENT_ID))
                .completed(true)
                .build();

        Auction auction = auctionDataMapper.createAuctionCommandToAuction(createAuctionCommand, PAYMENT_ID);
        auction.setId(new AuctionId(AUCTION_ID));

        when(userRepository.findUser(USER_ID)).thenReturn(Optional.of(user));
        when(paymentRepository.findPayment(PAYMENT_ID)).thenReturn(Optional.of(paymentResponse));

        when(auctionRepository.save(any(Auction.class))).thenReturn(auction);

    }

    @Test
    public void testCreateAuction() {
        CreateAuctionResponse createAuctionResponse = auctionApplicationService.createAuction(createAuctionCommand);
        assertEquals(AuctionStatus.ACTIVE, createAuctionResponse.getAuctionStatus());
        assertEquals("Auction created successfully", createAuctionResponse.getMessage());
    }


    @Test
    public void testCreateAuctionWhitWrongPrice() {
        AuctionDomainException auctionDomainException = assertThrows(AuctionDomainException.class,
                () -> auctionApplicationService.createAuction(createAuctionCommandWrongStartingPrice));

        assertEquals("Auction start price must be greater than zero", auctionDomainException.getMessage());
    }

    @Test
    public void testPlaceBid(){
        PlaceAuctionBidResponse placeAuctionBidResponse = auctionApplicationService.placeAuctionBid(placeAuctionBid);

    }

}


