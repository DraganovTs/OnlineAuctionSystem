package com.online.auction.system.auction.service.domain.ports.output.message.publisher.user;

import com.online.auction.system.auction.system.domain.event.AuctionPaymentProcessedEvent;
import com.online.auction.system.auction.system.domain.publisher.DomainEventPublisher;

public interface UserPaidRequestMessagePublisher extends DomainEventPublisher<AuctionPaymentProcessedEvent> {
}
