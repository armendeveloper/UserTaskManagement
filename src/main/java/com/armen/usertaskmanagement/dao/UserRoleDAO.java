package com.armen.usertaskmanagement.dao;

import java.util.List;

import com.armen.usertaskmanagement.model.UserRole;

public interface UserRoleDAO {
	/**
	 * Add user role
	 * @param userRole
	 */
	public void addUserRole(UserRole userRole);
	
	/**
	 * Get given user  user roles
	 * @param username
	 * @return
	 */
	public List<UserRole> getUserRoles(String username);
	
	/**
	 * Delete given user user roles
	 * @param username
	 */
	public void deleteUserRoles(String username);
	
}
