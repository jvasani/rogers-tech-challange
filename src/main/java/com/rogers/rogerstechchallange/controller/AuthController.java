package com.rogers.rogerstechchallange.controller;

import com.rogers.rogerstechchallange.entity.AuthRequest;
import com.rogers.rogerstechchallange.entity.AuthResponse;
import com.rogers.rogerstechchallange.service.CustomUserDetailsService;
import com.rogers.rogerstechchallange.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService service;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        authenticate(authRequest.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = service
                .loadUserByUsername(authRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
//        try {
//
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
//            );
//        } catch (Exception ex) {
//            throw new Exception("inavalid username/password");
//        }
//        return jwtUtil.generateToken(authRequest.getUserName());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
