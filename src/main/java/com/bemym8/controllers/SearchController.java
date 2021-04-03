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
///{searchRequest} @RequestParam String searchRequest,
    @GetMapping("/search")
    public String projectsPage(Model model){
        //Iterable<Project> project = projectRepository.findByBodyLike(searchRequest);
       /* // Filtering from admins posts
        Iterator<Project> iter = project.iterator();
        while(iter.hasNext()) {
            Project p = iter.next();
            if (p.getId() == 11 || p.getId() == 12 || p.getId() == 13) {
                iter.remove();
            }
        }*/
        Iterable<Project> project = projectRepository.findAllByOrderByIdAsc();
        model.addAttribute("title","BeMyM8 - Search results");
        model.addAttribute("project", project);
        return "search-result";
    }
}
