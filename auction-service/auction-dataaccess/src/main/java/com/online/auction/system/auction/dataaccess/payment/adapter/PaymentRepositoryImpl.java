package com.online.auction.system.auction.dataaccess.payment.adapter;

import com.online.auction.system.auction.dataaccess.payment.mapper.PaymentDataAccessMapper;
import com.online.auction.system.auction.dataaccess.payment.repository.PaymentJpaRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.PaymentRepository;
import com.online.auction.system.auction.system.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository, PaymentDataAccessMapper paymentDataAccessMapper) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.paymentDataAccessMapper = paymentDataAccessMapper;
    }

    @Override
    public Optional<Payment> findPayment(UUID paymentId) {
        return paymentJpaRepository.findById(paymentId).map(paymentDataAccessMapper::paymentEntityToPayment);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentDataAccessMapper.paymentEntityToPayment(paymentJpaRepository.save(
                paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }
}
