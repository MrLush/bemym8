package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectsJSONController {

    @Autowired
    private ProjectRepository projectRepository;

    //Answers with JSON below
    @GetMapping("/projects/JSON/{id}")
    public Project projectJSON(@RequestParam(value = "id") long id) {
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to access non-existent project");
        }
        return projectRepository.findById(id).orElseThrow(IllegalStateException::new);
    }
}
