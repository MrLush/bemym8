package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.models.User;
import com.bemym8.repo.ProjectRepository;
import com.bemym8.serv.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class ProjectActionsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects/add")
    public String addProject(Model model) {
        setDefaultProject(model);
        model.addAttribute("title", "Add new project");
        return "user/project-add";
    }

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

    //TODO add extra security - post belongs to their author

    @GetMapping("/projects/{id}/edit")
    public String projectEdit(@PathVariable(value = "id") long id, Model model){
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        model.addAttribute("project", project);
        return "user/project-edit";
    }
    @PostMapping("/projects/{id}/edit")
    public String projectEditSubmit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String shortDescription, @RequestParam String body, Model model){
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        project.setTitle(title);
        project.setShortDescription(shortDescription);
        project.setBody(body);
        projectRepository.save(project);
        model.addAttribute("title", "Editing project");
        System.out.println("project was successfully edited");
        return "redirect:/projects/{id}";
    }

    @PostMapping("/projects/{id}/remove")
    public String projectRemove(@PathVariable(value = "id") long id, Model model){
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to delete non-existent project");
            return "redirect:/projects";
        }
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        projectRepository.delete(project);
        System.out.println("project was successfully deleted");
        return "redirect:/projects";
    }
    private void setDefaultProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
    }
}
