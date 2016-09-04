package com.armen.usertaskmanagement.service;

import org.springframework.transaction.annotation.Transactional;
import com.armen.usertaskmanagement.dao.ProjectDAO;
import com.armen.usertaskmanagement.dao.TaskDAO;
import com.armen.usertaskmanagement.model.Project;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;
	private TaskDAO taskDAO;

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	@Transactional
	public void addProject(Project project) {
		this.projectDAO.addProject(project);
	}

	@Override
	@Transactional
	public void updateProject(Project project) {
		this.projectDAO.updateProject(project);
	}

	@Override
	@Transactional
	public List<Project> listProjects() {
		return this.projectDAO.listProjects();
	}

	@Override
	@Transactional
	public Project getProjectById(int id) {
		return this.projectDAO.getProjectById(id);
	}

	@Override
	@Transactional
	public void removeProject(int id) {
		Project project = getProjectById(id);
		this.taskDAO.deleteProjectTasks(project);
		this.projectDAO.removeProject(id);
	}

}
