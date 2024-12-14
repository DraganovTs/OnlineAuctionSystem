package com.online.auction.system.auction.service.domain.dto.create;

import com.online.auction.system.common.domain.valueobject.AuctionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PlaceAuctionBidResponse {
    @NotNull
    private final AuctionStatus auctionStatus;
    @NotNull
    private final String message;
    private final List<String> failureMessages;
}
