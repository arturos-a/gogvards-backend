package com.gogvards.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {
    @GetMapping("/init")
    public String index(Principal principal) {
        return principal.getName();
    }
}
