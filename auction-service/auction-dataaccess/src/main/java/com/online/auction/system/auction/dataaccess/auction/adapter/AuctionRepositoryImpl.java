package com.online.auction.system.auction.dataaccess.auction.adapter;

import com.online.auction.system.auction.dataaccess.auction.mapper.AuctionDataAccessMapper;
import com.online.auction.system.auction.dataaccess.auction.repository.AuctionJpaRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.AuctionRepository;
import com.online.auction.system.auction.system.domain.entity.Auction;

import java.util.Optional;
import java.util.UUID;

public class AuctionRepositoryImpl implements AuctionRepository {


    private final AuctionJpaRepository auctionJpaRepository;
    private final AuctionDataAccessMapper auctionDataAccessMapper;

    public AuctionRepositoryImpl(AuctionJpaRepository auctionJpaRepository, AuctionDataAccessMapper auctionDataAccessMapper) {
        this.auctionJpaRepository = auctionJpaRepository;
        this.auctionDataAccessMapper = auctionDataAccessMapper;
    }

    @Override
    public Auction save(Auction auction) {
        return auctionDataAccessMapper.auctionEntityToAuction(auctionJpaRepository
                .save(auctionDataAccessMapper.auctionToAuctionEntity(auction)));
    }

    @Override
    public Optional<Auction> findByName(String name) {
        return auctionJpaRepository.findByName(name).map(auctionDataAccessMapper::auctionEntityToAuction);
    }

    @Override
    public Optional<Auction> findById(UUID id) {
        return auctionJpaRepository.findById(id).map(auctionDataAccessMapper::auctionEntityToAuction);
    }
}
