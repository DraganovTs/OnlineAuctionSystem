package com.online.auction.system.auction.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PlaceAuctionBidCommand {

    @NotNull
    private final String userId;
    @NotNull
    private final BigDecimal bid;
}
