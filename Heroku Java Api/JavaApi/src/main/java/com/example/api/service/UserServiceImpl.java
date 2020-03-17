package com.example.api.service;

import com.example.api.exception.UsernameAlreadyExistsException;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User newUser){

        User user = userRepository.findByUsername(newUser.getUsername());
        if (user == null){
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }
        throw new UsernameAlreadyExistsException("Username '"+ newUser.getUsername() + "' already exists");

    }
}
