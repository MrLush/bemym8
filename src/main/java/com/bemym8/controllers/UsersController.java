package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.models.User;
import com.bemym8.repo.ProjectRepository;
import com.bemym8.repo.UserRepository;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/current-user")
    public User userJSON(@AuthenticationPrincipal UserDetailsWrapper user) {
        if (!userRepository.existsById(user.getId())){
            System.out.println("Error: trying to access non-existent project");
        }
        return userRepository.findById(user.getId()).orElseThrow(IllegalStateException::new);
    }
}
