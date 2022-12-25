package com.zzimkong.h2.user.domain.dto.mapper;

import com.zzimkong.h2.user.domain.User;
import com.zzimkong.h2.user.domain.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);

    @InheritInverseConfiguration
    User map(UserDto userDto);
}
