package me.slivernine.tutorial.service;

import lombok.RequiredArgsConstructor;
import me.slivernine.tutorial.dto.UserDto;
import me.slivernine.tutorial.entity.Authority;
import me.slivernine.tutorial.entity.User;
import me.slivernine.tutorial.repository.UserRepository;
import me.slivernine.tutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOnWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOnWithAuthoritiesByUsername(username);
    }

    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOnWithAuthoritiesByUsername);
    }
}
