package com.online.auction.system.auction.service.domain.ports.output.repository;

import com.online.auction.system.auction.system.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

   Optional<Payment> findPayment(UUID paymentId);
}
