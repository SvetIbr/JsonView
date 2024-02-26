package com.example.views.service;

import com.example.views.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    UserDto getById(Long id);

    List<UserDto> getAll();

    void deleteById(Long id);
}
