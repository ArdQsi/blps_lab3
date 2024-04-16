package com.webapp.controllers;

import com.webapp.auth.AuthenticationRequest;
import com.webapp.auth.RegisterRequest;
import com.webapp.dto.AuthenticationResponseDto;
import com.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rutube.ru")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}

