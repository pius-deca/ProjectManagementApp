package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.MapValidationErrorService;
import com.example.api.service.UserServiceImpl;
import com.example.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null){ return errorMap; }
        User newUser = userService.registerUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
