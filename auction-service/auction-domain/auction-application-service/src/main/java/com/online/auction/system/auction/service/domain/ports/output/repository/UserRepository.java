package com.online.auction.system.auction.service.domain.ports.output.repository;

import com.online.auction.system.auction.system.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUser(UUID userId);
}
