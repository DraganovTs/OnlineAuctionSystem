package com.online.auction.system.auction.dataaccess.auction.repository;

import com.online.auction.system.auction.dataaccess.auction.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuctionJpaRepository extends JpaRepository<AuctionEntity, UUID> {

    Optional<AuctionEntity> findById(UUID id);

    Optional<AuctionEntity> findByName(String name);
}
