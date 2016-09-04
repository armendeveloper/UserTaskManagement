package com.armen.usertaskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.service.ProjectService;
import com.armen.usertaskmanagement.service.TaskService;
import com.armen.usertaskmanagement.service.UserService;

@Controller
public class MainController {

	private UserService userService;
	
	private ProjectService projectService;
	
	private TaskService taskService;
	
	
	@Autowired
	@Qualifier(value = "projectService")
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(value = "taskService")
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = { "/","/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		User loggedInUser = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
			loggedInUser = this.userService.getUserByUsername(userDetails.getUsername());			
		}
		ModelAndView model = null;
		if(loggedInUser != null){
			model = new ModelAndView();
			model.addObject("title", "Spring Security Login Form - Database Authentication");
			model.addObject("message", "This is default page!");
			model.setViewName("hello");
			List<Task> tasks = this.taskService.getUserTasks(loggedInUser);
			model.addObject("tasks", tasks);
		}else{
			model = new ModelAndView("redirect:login");
		}
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}

}