package com.zzimkong.h2.user.service;

import com.zzimkong.h2.user.domain.User;
import com.zzimkong.h2.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(User.builder()
                        .password(passwordEncoder.encode(user.getPassword()))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .build())
                .doOnSuccess(u -> log.info("Created new user with ID = " + u.getId()));
    }

    public Mono<User> getUser(String name) {
        return userRepository.findByName(name);
    }
}
