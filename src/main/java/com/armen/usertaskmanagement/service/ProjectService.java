package com.armen.usertaskmanagement.service;

import java.util.List;

import com.armen.usertaskmanagement.model.Project;

public interface ProjectService {
	/**
	 * Add project
	 * @param project
	 */
	public void addProject(Project project);
	
	/**
	 * Update project
	 * @param project
	 */
	public void updateProject(Project project);
	
	/**
	 * Load all projects
	 * @return
	 */
	public List<Project> listProjects();
	
	/**
	 * Load project by id
	 * @param id
	 * @return
	 */
	public Project getProjectById(int id);
	
	/**
	 * Remove project by id
	 * @param id
	 */
	public void removeProject(int  id);

}
