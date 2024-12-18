package com.online.auction.system.auction.dataaccess.payment.mapper;

import com.online.auction.system.auction.dataaccess.payment.entity.PaymentEntity;
import com.online.auction.system.auction.system.domain.entity.Payment;
import com.online.auction.system.common.domain.valueobject.PaymentId;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId().getValue())
                .completed(payment.isCompleted())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(new PaymentId(paymentEntity.getId()))
                .completed(paymentEntity.isCompleted())
                .build();
    }

}
