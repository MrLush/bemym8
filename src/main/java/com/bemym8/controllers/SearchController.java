package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

@Controller
public class SearchController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/search")
    public String projectsPage(@RequestParam String searchRequest, Model model){
        model.addAttribute("title","BeMyM8 - Search");
        Iterable<Project> project = projectRepository.findByBodyLike(searchRequest);
        // Filtering from admins posts
        Iterator<Project> iter = project.iterator();
        while(iter.hasNext()) {
            Project p = iter.next();
            if (p.getId() == 6 || p.getId() == 7|| p.getId() == 11) {
                iter.remove();
            }
        }
        model.addAttribute("title","BeMyM8 - Projects");
        model.addAttribute("project", project);
        return "search-result";
    }
}
