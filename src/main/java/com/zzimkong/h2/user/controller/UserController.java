package com.zzimkong.h2.user.controller;

import com.zzimkong.h2.user.domain.dto.UserDto;
import com.zzimkong.h2.user.domain.dto.mapper.UserMapper;
import com.zzimkong.h2.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Mono<UserDto> getUser(String name) {
        return userService.getUser(name)
                .map(userMapper::map);
    }

    @PostMapping
    public Mono<UserDto> newUser(@RequestBody UserDto userDto) {
        return userService.createUser(userMapper.map(userDto)).map(userMapper::map);
    }
}
