package com.armen.usertaskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.service.ProjectService;
import com.armen.usertaskmanagement.service.UserService;

@Controller
public class ProjectController {
	
	private ProjectService projectService;
	

	@Autowired
	@Qualifier(value = "projectService")
	public void setUserService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("listProjects", this.projectService.listProjects());
		return "project";
	}
	
	//For add and update project both
	@RequestMapping(value= "/project/add", method = RequestMethod.POST)
	public String addProject(@ModelAttribute("project") Project project){
		
		if(project.getId() == 0){
			//new project, add it
			this.projectService.addProject(project);
		}else{
			//existing project, call update
			this.projectService.updateProject(project);
		}
		
		return "redirect:/projects";
		
	}
	
	@RequestMapping("/project/remove/{id}")
    public String removeProject(@PathVariable("id") int id){
		
        this.projectService.removeProject(id);
        return "redirect:/projects";
    }
 
    @RequestMapping("/project/edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model){
        model.addAttribute("project", this.projectService.getProjectById(id));
        model.addAttribute("listProjects", this.projectService.listProjects());
        return "project";
    }
	
}
