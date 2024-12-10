package com.online.auction.system.auction.service.domain.ports.output.message.publisher.payment;

import com.online.auction.system.auction.system.domain.event.AuctionCanceledEvent;
import com.online.auction.system.auction.system.domain.publisher.DomainEventPublisher;

public interface BidCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<AuctionCanceledEvent> {
}
