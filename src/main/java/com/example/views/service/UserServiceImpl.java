package com.example.views.service;

import com.example.views.dto.UserDto;
import com.example.views.exception.UserNotFoundException;
import com.example.views.mapper.UserMapper;
import com.example.views.model.User;
import com.example.views.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.views.util.Constants.MESSAGE;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user = repository.save(user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserDto userDto) {
        User userToUpdate = repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format(MESSAGE , id)));

        userToUpdate = userMapper.update(userToUpdate);

        return userMapper.toUserDto(repository.save(userToUpdate));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getById(Long id) {
        User user = repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format(MESSAGE , id)));
        return userMapper.toUserDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAll() {
        return repository.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(String.format(MESSAGE , id));
        }
        repository.deleteById(id);
    }
}
