package com.armen.usertaskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.service.ProjectService;
import com.armen.usertaskmanagement.service.TaskService;
import com.armen.usertaskmanagement.service.UserService;

@Controller
public class UserController {

	private UserService userService;
	

	@Autowired
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUsers());
		return "user";
	}
	
	//For add and update user both
	@RequestMapping(value= "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user){
		
		User exsistedUser = this.userService.getUserByUsername(user.getUsername());
		if(exsistedUser == null){
			//new user, add it
			this.userService.addUser(user);
		}else{
			//existing user, call update
			this.userService.updateUser(user);
		}
		
		return "redirect:/users";
		
	}
	
	@RequestMapping("/user/remove/{username}")
    public String removeUser(@PathVariable("username") String username){
		
        this.userService.removeUser(username);
        return "redirect:/users";
    }
 
    @RequestMapping("/user/edit/{username}")
    public String editUser(@PathVariable("username") String username, Model model){
        model.addAttribute("user", this.userService.getUserByUsername(username));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }
}
