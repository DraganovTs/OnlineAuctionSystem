package com.online.auction.system.auction.service.domain.dto.create;

import com.online.auction.system.common.domain.valueobject.AuctionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateAuctionResponse {
    @NotNull
    private final AuctionStatus auctionStatus;
    @NotNull
    private final String message;
}
