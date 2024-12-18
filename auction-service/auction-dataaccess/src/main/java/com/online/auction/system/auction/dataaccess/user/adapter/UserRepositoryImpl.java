package com.online.auction.system.auction.dataaccess.user.adapter;

import com.online.auction.system.auction.dataaccess.user.mapper.UserDataAccessDataMapper;
import com.online.auction.system.auction.dataaccess.user.repository.UserJpaRepository;
import com.online.auction.system.auction.service.domain.ports.output.repository.UserRepository;
import com.online.auction.system.auction.system.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessDataMapper userDataAccessDataMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessDataMapper userDataAccessDataMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessDataMapper = userDataAccessDataMapper;
    }

    @Override
    public Optional<User> findUser(UUID userId) {
        return userJpaRepository.findByUser(userId).map(userDataAccessDataMapper::userEntityToUser);
    }
}
