package com.qlsp.quanlysanpham.websecurity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRestController {
    
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

}
