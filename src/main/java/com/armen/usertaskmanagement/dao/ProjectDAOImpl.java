package com.armen.usertaskmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armen.usertaskmanagement.model.Project;


public class ProjectDAOImpl implements ProjectDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProject(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(project);
		logger.info("Project saved successfully, Project Details=" + project);
	}

	@Override
	public void updateProject(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(project);
		logger.info("Project updated successfully, Project Details=" + project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProjects() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Project> projectsList = session.createQuery("from Project").list();
		for(Project project : projectsList){
			logger.info("Project List::" + project);
		}
		return projectsList;
	}

	@Override
	public Project getProjectById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Project project = (Project) session.load(Project.class, new Integer(id));
		logger.info("Project loaded successfully, Project details=" + project);
		return project;
	}

	@Override
	public void removeProject(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Project project = (Project) session.load(Project.class, new Integer(id));
		if(null != project){
			session.delete(project);
		}
		logger.info("Project deleted successfully, Project details=" + project);
	}
}
