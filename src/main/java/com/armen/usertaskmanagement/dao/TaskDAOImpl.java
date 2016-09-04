package com.armen.usertaskmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.model.UserRole;

public class TaskDAOImpl implements TaskDAO {

	private static final Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addTask(Task task) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(task);
		logger.info("Task saved successfully, Task Details=" + task);
	}

	@Override
	public void updateTask(Task task) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(task);
		logger.info("Task updated successfully, Task Details=" + task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Task> tasksList = session.createQuery("from Task").list();
		for(Task task : tasksList){
			logger.info("Task List::" + task);
		}
		return tasksList;
	}

	@Override
	public Task getTaskById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Task task = (Task) session.load(Task.class, new Integer(id));
		logger.info("Task loaded successfully, Task details=" + task);
		return task;
	}

	@Override
	public void removeTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Task task = (Task) session.load(Task.class, new Integer(id));
		if(null != task){
			session.delete(task);
		}
		logger.info("Task deleted successfully, Task details=" + task);
	}
	
	@Override
	public List<Task> getUserTasks(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "Select * From Tasks Where username = :username";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Task.class);
		query.setParameter("username", user.getUsername());
		List<Task> tasks = query.list();
		logger.info("User- " + user.getUsername() + " tasks loaded successfully " + tasks);
		return tasks;
	}

	@Override
	public List<Task> getProjectTasks(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "Select * From Tasks Where project_id = :project_id";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Task.class);
		query.setParameter("project_id", project.getId());
		List<Task> tasks = query.list();
		logger.info("Project- " + project.getName() + " tasks loaded successfully " + tasks);
		return tasks;
	}

	@Override
	public void deleteUserTasks(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Task> tasks = getUserTasks(user);
		for(Task task : tasks){
			session.delete(task);
		}
		
	}

	@Override
	public void deleteProjectTasks(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Task> tasks = getProjectTasks(project);
		for(Task task : tasks){
			session.delete(task);
		}		
	}

}
