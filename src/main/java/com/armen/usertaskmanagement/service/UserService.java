package com.armen.usertaskmanagement.service;

import java.util.List;

import com.armen.usertaskmanagement.model.User;

public interface UserService {
	
	/**
	 * Add user
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * Update user
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * Load all users
	 * @return
	 */
	public List<User> listUsers();
	
	/**
	 * Load user by user name
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Remove user by username
	 * @param username
	 */
	public void removeUser(String  username);
}
