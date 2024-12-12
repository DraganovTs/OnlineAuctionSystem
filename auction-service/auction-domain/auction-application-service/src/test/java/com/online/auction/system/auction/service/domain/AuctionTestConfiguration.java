package com.online.auction.system.auction.service.domain;

import com.online.auction.system.auction.service.domain.ports.output.message.publisher.messages.AuctionCreatedMessageRequestMessagePublisher;
import com.online.auction.system.auction.service.domain.ports.output.message.publisher.payment.BidCancelledPaymentRequestMessagePublisher;
import com.online.auction.system.auction.service.domain.ports.output.message.publisher.payment.BidCreatedPaymentRequestMessagePublisher;
import com.online.auction.system.auction.service.domain.ports.output.message.publisher.user.UserPaidRequestMessagePublisher;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.PaymentRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.AuctionDomainService;
import com.online.auction.system.auction.system.domain.AuctionDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.online.auction.system")
public class AuctionTestConfiguration {


    @Bean
    public AuctionCreatedMessageRequestMessagePublisher auctionCreatedMessageRequestMessagePublisher() {
        return Mockito.mock(AuctionCreatedMessageRequestMessagePublisher.class);
    }

    @Bean
    public BidCreatedPaymentRequestMessagePublisher bidCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(BidCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public BidCancelledPaymentRequestMessagePublisher auctionBidCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(BidCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public UserPaidRequestMessagePublisher userPaidRequestMessagePublisher() {
        return Mockito.mock(UserPaidRequestMessagePublisher.class);
    }

    @Bean
    public AuctionRepository auctionRepository() {
        return Mockito.mock(AuctionRepository.class);
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return Mockito.mock(PaymentRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public AuctionDomainService auctionDomainService() {
        return new AuctionDomainServiceImpl();
    }


}
