package com.online.auction.system.auction.dataaccess.payment.repository;

import com.online.auction.system.auction.dataaccess.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findPayment(UUID paymentId);

}
