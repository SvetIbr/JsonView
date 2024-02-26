package com.example.views.mapper;

import com.example.views.dto.UserDto;
import com.example.views.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User update(User user);
}
