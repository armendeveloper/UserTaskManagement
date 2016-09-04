package com.armen.usertaskmanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.armen.usertaskmanagement.dao.TaskDAO;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.User;

public class TaskServiceImpl implements TaskService {

	private TaskDAO taskDAO;

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	@Transactional
	public void addTask(Task task) {
		this.taskDAO.addTask(task);
	}

	@Override
	@Transactional
	public void updateTask(Task task) {
		this.taskDAO.updateTask(task);
	}

	@Override
	@Transactional
	public List<Task> listTasks() {
		return this.taskDAO.listTasks();
	}

	@Override
	@Transactional
	public Task getTaskById(int id) {
		return this.taskDAO.getTaskById(id);
	}

	@Override
	@Transactional
	public void removeTask(int id) {
		this.taskDAO.removeTask(id);
	}

	@Override
	@Transactional
	public List<Task> getUserTasks(User user) {
		return this.taskDAO.getUserTasks(user);
	}

	@Override
	@Transactional
	public List<Task> getProjectTasks(Project project) {
		return this.taskDAO.getProjectTasks(project);
	}

	
}
