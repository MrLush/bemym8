package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.models.User;
import com.bemym8.repo.ProjectRepository;
import com.bemym8.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("/api")
@RestController
public class APIController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * @param username - current username from mobile app
     * @return - all parameters of user wrapped as JSON
     */
    @GetMapping("/user")
    public User userJSON(@RequestParam(value = "username", defaultValue = "unknown") String username) {
        //TODO Don't return password
        User user = userRepository.findByUsername(username);
        return user;
    }


    /**
     * @return - all projects wrapped as JSON
     */
    @GetMapping("/projects/all")
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

    /**
     * @param id - some project id
     * @return - all information of that project wrapped as JSON
     */
    @GetMapping("/projects")
    public Project projectJSON(@RequestParam(value = "id", defaultValue = "2") Long id) {
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to access non-existent project");
        }
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        return project;
    }

    /**
     * @return - returning some projects, created by admin, for support page
     */
    @GetMapping("/support")
    public Iterable<Project> projectsPage(){
        // Here is id's of post for page support
        Iterable<Long> ids = Arrays.asList(11L, 12L, 13L);
        Iterable<Project> posts = projectRepository.findAllById(ids);
        List<Project> sorted = StreamSupport.stream(
                posts.spliterator(), false)
                .sorted((p1, p2) -> ((Long)p1.getId())
                        .compareTo(p2.getId()))
                .collect(Collectors.toList());
        return posts;
    }
}
