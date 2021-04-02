package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
public class ProjectsJSONController {

    @Autowired
    private ProjectRepository projectRepository;


    @GetMapping("/projects/JSON/all")
    public Iterable<Project> projectJSON() {
        // Filtering from admins posts
        Iterable<Project> project = projectRepository.findAllByOrderByIdAsc();
        Iterator<Project> iter = project.iterator();
        while(iter.hasNext()) {
            Project p = iter.next();
            if (p.getId() == 11 || p.getId() == 12 || p.getId() == 13) {
                iter.remove();
            }
        }
        return project;
    }
    @GetMapping("/projects/JSON")
    public Project projectJSON(@RequestParam(value = "id", defaultValue = "2") Long id) {
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to access non-existent project");
        }
        return projectRepository.findById(id).orElseThrow(IllegalStateException::new);
    }
}
