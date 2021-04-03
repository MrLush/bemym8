package com.bemym8.controllers;

import com.bemym8.models.User;
import com.bemym8.repo.UserRepository;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAuthority('USER')")
@Controller
public class AccountController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/account")
    public String accountPage(Model model, @AuthenticationPrincipal UserDetailsWrapper user){
        if (!userRepository.existsById(user.getId())){
            System.out.println("Error: trying to access non-existent project");
            return "redirect:/";
        }
        User curUser = userRepository.findByUsername(user.getUsername());

        model.addAttribute("title","BeMyM8 - Account");
        model.addAttribute("user", curUser);
        return "user/account";
    }

    @PostMapping("/account")
    public String accountPageSubmit(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, Model model){
        try{
            User user = userRepository.findByUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userRepository.save(user);
            System.out.println("user was successfully edited");
            return "redirect:/account";
        }
        catch(Exception e){throw new IllegalStateException();}
    }
}
