package com.online.auction.system.auction.service.domain.dto.create;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAuctionCommand {
    @NotNull
    private final UUID userId;
    @NotNull
    private final UUID paymentId;
    @NotNull
    private final BigDecimal startPrice;
    @NotNull
    @Size(min = 1, max = 50)
    private final String title;
    @NotNull
    @Size(min = 1, max = 50)
    private final String description;
}
