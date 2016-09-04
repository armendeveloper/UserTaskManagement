package com.armen.usertaskmanagement.dao;

import java.util.List;

import com.armen.usertaskmanagement.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUsers();
	public User getUserByUsername(String username);
	public void removeUser(String  username);
}


