package com.online.auction.system.auction.service.domain.ports.input.message.listener.payment;

import com.online.auction.system.auction.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
