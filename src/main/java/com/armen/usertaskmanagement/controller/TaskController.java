package com.armen.usertaskmanagement.controller;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.propertyeditors.ProjectEditor;
import com.armen.usertaskmanagement.propertyeditors.UserEditor;
import com.armen.usertaskmanagement.service.ProjectService;
import com.armen.usertaskmanagement.service.TaskService;
import com.armen.usertaskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TaskController {
	
	private ProjectService projectService;
	
	private UserService userService;
	
	private TaskService taskService;

	private UserEditor userEditor;

	private ProjectEditor projectEditor;


	@Autowired
	@Qualifier(value = "projectService")
	public void setUserService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	@Qualifier(value = "taskService")
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(value = "userEditor")
	public void setUserEditor(UserEditor userEditor) {
		this.userEditor = userEditor;
	}

	@Autowired
	@Qualifier(value = "projectEditor")
	public void setProjectEditor(ProjectEditor projectEditor) {
		this.projectEditor = projectEditor;
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public String listTasks(Model model) {
		model.addAttribute("task", new Task());
		List<User> users = this.userService.listUsers();
		List<Project> projects = this.projectService.listProjects();
		model.addAttribute("listTasks", this.taskService.listTasks());
		model.addAttribute("listUsers", users);
		model.addAttribute("listProjects", projects);
		return "task";
	}
	
	//For add and update task both
	@RequestMapping(value= "/task/add", method = RequestMethod.POST)
	public String addTask(@ModelAttribute("task") Task task){
		if(task.getId() == 0){
			//new task, add it
			this.taskService.addTask(task);
		}else{
			//existing task, call update
			this.taskService.updateTask(task);
		}
		
		return "redirect:/tasks";
		
	}
	
	@RequestMapping("/task/remove/{id}")
    public String removeTask(@PathVariable("id") int id){
		
        this.taskService.removeTask(id);
        return "redirect:/tasks";
    }
 
    @RequestMapping("/task/edit/{id}")
    public String editTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", this.taskService.getTaskById(id));
        model.addAttribute("listTasks", this.taskService.listTasks());
        List<User> users = this.userService.listUsers();
		List<Project> projects = this.projectService.listProjects();
		model.addAttribute("listUsers", users);
		model.addAttribute("listProjects", projects);
        return "task";
    }

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(User.class, this.userEditor);
		binder.registerCustomEditor(Project.class, this.projectEditor);
	}
	

}
