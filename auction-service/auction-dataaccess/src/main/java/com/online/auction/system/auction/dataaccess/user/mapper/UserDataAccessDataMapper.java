package com.online.auction.system.auction.dataaccess.user.mapper;

import com.online.auction.system.auction.dataaccess.user.entity.UserEntity;
import com.online.auction.system.auction.system.domain.entity.User;
import com.online.auction.system.common.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessDataMapper {

    public User userEntityToUser( UserEntity userEntity) {
        return new User(new UserId(userEntity.getId()));
    }


}
