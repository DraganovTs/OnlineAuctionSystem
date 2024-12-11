package com.online.auction.system.auction.service.domain.ports.output.message.publisher.messages;

import com.online.auction.system.auction.system.domain.event.AuctionCreatedEvent;
import com.online.auction.system.auction.system.domain.publisher.DomainEventPublisher;

public interface AuctionCreatedMessageRequestMessagePublisher extends DomainEventPublisher<AuctionCreatedEvent> {
}
