package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.models.User;
import com.bemym8.repo.ProjectRepository;
import com.bemym8.repo.UserRepository;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class ProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/projects")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Projects");
        Iterable<Project> project = projectRepository.findAllByOrderByIdAsc();
        // Filtering from admins posts
        Iterator<Project> iter = project.iterator();
        while(iter.hasNext()) {
            Project p = iter.next();
            if (p.getId() == 11 || p.getId() == 12 || p.getId() == 13) {
                iter.remove();
            }
        }

        model.addAttribute("project", project);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String projectPage(@PathVariable(value = "id") long id, Model model){
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to access non-existent project");
            return "redirect:/projects";
        }
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        Optional<User> author = userRepository.findById(project.getAuthorId());
        model.addAttribute("author", author.get());
        model.addAttribute("project", project);
        return "project-details";
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/projects/add")
    public String addProject(Model model) {
        setDefaultProject(model);
        model.addAttribute("title", "Add new project");
        return "user/project-add";
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/projects/add")
    public String addProjectSubmit(@RequestParam String title, @RequestParam String shortDescription, @RequestParam String body, Model model, Project project, @AuthenticationPrincipal UserDetailsWrapper user) {
        System.out.println("Title is " + project.getTitle());
        System.out.println("Short Description is " + project.getShortDescription());
        System.out.println("Body is " + project.getBody());
        System.out.println("Author is " + user.getId());
        project.setAuthorId(user.getId());
        projectRepository.save(project);
        System.out.println("New project was successfully added");
        return "redirect:/projects";
    }

    @GetMapping("/projects/{id}/edit")
    public String projectEdit(@PathVariable(value = "id") long id, Model model, @AuthenticationPrincipal UserDetailsWrapper user){
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        if (!isEditableByCurrentUser(user, project)){
            return "redirect:/projects/{id}";
        }
        model.addAttribute("project", project);
        return "user/project-edit";
    }

    @PostMapping("/projects/{id}/edit")
    public String projectEditSubmit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String shortDescription, @RequestParam String body, Model model, @AuthenticationPrincipal UserDetailsWrapper user){
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        if (!isEditableByCurrentUser(user, project)){
            return "redirect:/projects/{id}";
        }
        project.setTitle(title);
        project.setShortDescription(shortDescription);
        project.setBody(body);
        projectRepository.save(project);
        model.addAttribute("title", "Editing project");
        System.out.println("project was successfully edited");
        return "redirect:/projects/{id}";
    }

    @PostMapping("/projects/{id}/remove")
    public String projectRemove(@PathVariable(value = "id") long id, Model model, @AuthenticationPrincipal UserDetailsWrapper user){
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to delete non-existent project");
            return "redirect:/projects";
        }
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        if (!isEditableByCurrentUser(user, project)){
            return "redirect:/projects/{id}";
        }
        projectRepository.delete(project);
        System.out.println("project was successfully deleted");
        return "redirect:/projects";
    }

    private void setDefaultProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
    }

    // User can edit and delete project if he is author or admin
    public boolean isEditableByCurrentUser(UserDetailsWrapper user, Project project){
        return user.getId() == project.getAuthorId() || user.isAdmin();
    }
}
