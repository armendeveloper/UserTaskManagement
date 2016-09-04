package com.armen.usertaskmanagement.dao;

import java.util.List;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.User;


public interface TaskDAO {
	public void addTask(Task task);
	public void updateTask(Task task);
	public List<Task> listTasks();
	public Task getTaskById(int id);
	public void removeTask(int id);
	public List<Task> getUserTasks(User user);
	public List<Task> getProjectTasks(Project project);
	
	/**
	 * Delete tasks of given user
	 * @param user
	 */
	public void deleteUserTasks(User user);
	
	/**
	 * Delete tasks of given project
	 * @param project
	 */
	public void deleteProjectTasks(Project project);
}
