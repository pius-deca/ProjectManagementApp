package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.payload.JWTLoginSuccessResponse;
import com.example.api.payload.LoginRequest;
import com.example.api.security.JWTProvider;
import com.example.api.service.MapValidationErrorService;
import com.example.api.service.UserServiceImpl;
import com.example.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.api.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null){
            return errorMap;
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = TOKEN_PREFIX + jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, token));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null){ return errorMap; }
        User newUser = userService.registerUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
