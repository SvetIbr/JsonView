package com.example.views.controller;

import com.example.views.view.Views;
import com.example.views.dto.UserDto;
import com.example.views.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService service;
    @GetMapping
    @JsonView(Views.UserSummary.class)
    public List<UserDto> findAll() {
        return service.getAll();
    }

    @PostMapping
    @JsonView(Views.UserDetails.class)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(service.create(userDto), HttpStatus.CREATED).getBody();
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserDetails.class)
    public UserDto findById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    @JsonView(Views.UserDetails.class)
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) {
        return service.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
