package com.online.auction.system.autction.application.exceptions;

import com.online.auction.application.handler.ErrorDTO;
import com.online.auction.application.handler.GlobalExceptionHandler;
import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.auction.system.domain.exception.AuctionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class AuctionGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {AuctionDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(AuctionDomainException auctionDomainException){
        log.error(auctionDomainException.getMessage(),auctionDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(auctionDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {AuctionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(AuctionNotFoundException auctionNotFoundException){
        log.error(auctionNotFoundException.getMessage(),auctionNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(auctionNotFoundException.getMessage())
                .build();
    }
}
