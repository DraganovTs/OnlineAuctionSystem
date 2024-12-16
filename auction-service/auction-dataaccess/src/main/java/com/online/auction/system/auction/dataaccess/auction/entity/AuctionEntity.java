package com.online.auction.system.auction.dataaccess.auction.entity;

import com.online.auction.system.common.domain.valueobject.AuctionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class AuctionEntity {

    @Id
    private UUID id;
    private UUID paymentId;
    private UUID userId;
    private String title;
    private String description;
    private BigDecimal startPrice;
    private LocalDateTime startTime;
    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;
    private BigDecimal highestBid;
    private LocalDateTime endTime;
    private String failureMessages;
}
