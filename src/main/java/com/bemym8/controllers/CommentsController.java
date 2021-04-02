package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("projects/{id}")
    public String addProjectSubmit(@RequestParam String body, Model model, Project project, @AuthenticationPrincipal UserDetailsWrapper user) {

        project.setAuthorId(user.getId());
        System.out.println("New project was successfully added");
        return "redirect:/projects";
    }
}
