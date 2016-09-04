package com.armen.usertaskmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.model.UserRole;

public class UserRoleDAOImpl implements UserRoleDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserRole(UserRole userRole) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userRole);
		logger.info("User Role saved successfully, User Role Details=" + userRole);			
	}

	@Override
	public List<UserRole> getUserRoles(String username) {
		Session session = this.sessionFactory.getCurrentSession();	
		String sql = "Select * From UserRoles Where username = :username";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(UserRole.class);
		query.setParameter("username", username);
		List<UserRole> userRoles = query.list();
		logger.info("User Roles loaded successfully, User Roles =" + userRoles);			
		return userRoles;
	}

	@Override
	public void deleteUserRoles(String username) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<UserRole> userRoles = getUserRoles(username);
		for(UserRole userRole : userRoles){
			session.delete(userRole);
		}
	}

}
