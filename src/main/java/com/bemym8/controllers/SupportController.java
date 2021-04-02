package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class SupportController {

    @Autowired
    private ProjectRepository projectRepository;
    private long id ;

    @GetMapping("/support")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Support");

        // Here is id's of post for page support
        Iterable<Long> ids = Arrays.asList(11L, 12L, 13L);
        Iterable<Project> project = projectRepository.findAllById(ids);
        Stream<Project> sorted = StreamSupport.stream(
                project.spliterator(), false)
                .sorted((p1, p2) -> ((Long)p1.getId()).compareTo(p2.getId()));
        model.addAttribute("project", sorted);
        return "support";
    }
}
