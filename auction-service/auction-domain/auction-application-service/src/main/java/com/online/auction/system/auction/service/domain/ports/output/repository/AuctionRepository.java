package com.online.auction.system.auction.service.domain.ports.output.repository;

import com.online.auction.system.auction.system.domain.entity.Auction;

import java.util.Optional;
import java.util.UUID;

public interface AuctionRepository {
    Auction save(Auction auction);

    Optional<Auction> findByName(String name);

    Optional<Auction> findById(UUID id);

}
