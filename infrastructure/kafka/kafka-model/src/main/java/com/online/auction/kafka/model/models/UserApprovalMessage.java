package com.online.auction.kafka.model.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApprovalMessage {

    private String id;
    private String sagaId;
    private String auctionId;
    private String paymentId;
    private String customerId;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private String paymentStatus;
    private List<String> failureMessages;
}
