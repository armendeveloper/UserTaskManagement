package com.armen.usertaskmanagement.dao;

import java.util.List;

import com.armen.usertaskmanagement.model.Project;


public interface ProjectDAO {
	public void addProject(Project project);
	public void updateProject(Project project);
	public List<Project> listProjects();
	public Project getProjectById(int id);
	public void removeProject(int id);
}
