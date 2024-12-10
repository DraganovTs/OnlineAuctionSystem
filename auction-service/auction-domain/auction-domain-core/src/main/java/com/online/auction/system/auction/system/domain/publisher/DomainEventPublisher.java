package com.online.auction.system.auction.system.domain.publisher;

import com.online.auction.system.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
