package com.armen.usertaskmanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.armen.usertaskmanagement.dao.TaskDAO;
import com.armen.usertaskmanagement.dao.UserDAO;
import com.armen.usertaskmanagement.dao.UserRoleDAO;
import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.model.UserRole;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private UserRoleDAO userRoleDAO;
	private TaskDAO taskDAO;
	
	
	
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		this.userDAO.addUser(user);
		UserRole userRole = new UserRole();
		userRole.setRole(UserRole.ROLEUSER);
		userRole.setUser(user);
		this.userRoleDAO.addUserRole(userRole);	
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		this.userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		return this.userDAO.getUserByUsername(username);
	}

	@Override
	@Transactional
	public void removeUser(String username) {
		User user = getUserByUsername(username);
		this.taskDAO.deleteUserTasks(user);
		this.userRoleDAO.deleteUserRoles(username);
		this.userDAO.removeUser(username);	
	}

}
