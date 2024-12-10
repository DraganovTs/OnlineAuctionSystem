package com.online.auction.system.auction.system.domain.exception;

import com.online.auction.system.common.domain.exception.DomainException;

public class OrderDomainException extends DomainException {


    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
