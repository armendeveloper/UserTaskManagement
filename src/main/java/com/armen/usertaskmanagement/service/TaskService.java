package com.armen.usertaskmanagement.service;

import java.util.List;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.User;

public interface TaskService {
	
	/**
	 * Add task
	 * @param task
	 */
	public void addTask(Task task);
	
	/**
	 * Update task
	 * @param task
	 */
	public void updateTask(Task task);
	
	/**
	 * load list of tasks
	 * @return
	 */
	public List<Task> listTasks();
	
	/**
	 * Load task by id
	 * @param id
	 * @return
	 */
	public Task getTaskById(int id);
	
	/**
	 * Remove task by id
	 * @param id
	 */
	public void removeTask(int id);
	
	/**
	 * Load tasks of given user
	 * @param user
	 * @return
	 */
	public List<Task> getUserTasks(User user);
	
	/**
	 * Load tasks of given project
	 * @param project
	 * @return
	 */
	public List<Task> getProjectTasks(Project project);
	
	
}
