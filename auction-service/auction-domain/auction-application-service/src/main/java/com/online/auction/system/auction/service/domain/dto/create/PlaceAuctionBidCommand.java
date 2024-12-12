package com.online.auction.system.auction.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PlaceAuctionBidCommand {

    @NotNull
    private final UUID userId;
    @NotNull
    private final UUID auctionId;
    @NotNull
    private final BigDecimal bid;
}
