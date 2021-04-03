package com.bemym8.controllers;

import com.bemym8.models.Comment;
import com.bemym8.models.Project;
import com.bemym8.repo.CommentRepository;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("projects/{id}")
    public String addProjectSubmit(@RequestParam String content, Project project, @AuthenticationPrincipal UserDetailsWrapper user) {
        if(content == null || content.length() < 10 || content.length() > 1000){
            return "redirect:/projects";
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser_id(user.getId());
        comment.setUser_firstName(user.getFirstName());
        comment.setUser_lastName(user.getLastName());
        comment.setCreatedTimestamp(new Date(System.currentTimeMillis()));
        comment.setProjectBO(project);
        commentRepository.save(comment);
        System.out.println("New comment was successfully added");
        return "redirect:/projects/{id}";
    }

}
