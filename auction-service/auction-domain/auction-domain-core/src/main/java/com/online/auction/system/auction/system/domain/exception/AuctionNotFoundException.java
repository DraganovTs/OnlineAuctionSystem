package com.online.auction.system.auction.system.domain.exception;

import com.online.auction.system.common.domain.exception.DomainException;

public class AuctionNotFoundException extends DomainException {
    public AuctionNotFoundException(String message) {
        super(message);
    }

    public AuctionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
