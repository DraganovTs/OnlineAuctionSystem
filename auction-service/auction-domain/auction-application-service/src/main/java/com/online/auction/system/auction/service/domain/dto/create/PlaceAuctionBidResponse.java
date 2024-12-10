package com.online.auction.system.auction.service.domain.dto.create;

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
    private final String title;
    @NotNull
    private final String message;
    private final List<String> failureMessages;
}
