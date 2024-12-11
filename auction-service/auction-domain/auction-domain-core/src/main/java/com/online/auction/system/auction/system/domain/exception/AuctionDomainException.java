package com.online.auction.system.auction.system.domain.exception;

import com.online.auction.system.common.domain.exception.DomainException;

public class AuctionDomainException extends DomainException {


    public AuctionDomainException(String message) {
        super(message);
    }

    public AuctionDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
