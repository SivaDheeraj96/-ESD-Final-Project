package com.esd.app.dao;


import org.hibernate.query.Query;

import org.hibernate.HibernateException;
import org.hibernate.id.UUIDGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esd.app.exception.UserException;
import com.esd.app.pojo.User;

@Component
public class UserDAO extends DAO {

	 	public UserDAO() {
	    }

	    public User get(String username) throws UserException {
	    	 try {
	            
	         	begin();
	         	Query q = getSession().createQuery("from User where email= :username");
	         	System.out.println(username);
	         	q.setParameter("username", username);
	         	System.out.println(q.getResultList().size());
	         	User user = (User) q.uniqueResult();
	         
	         	commit();
	         	return user;
	         	
	         } catch (HibernateException e) {
	             rollback();
	             throw new UserException("Could not create user " + username, e);
	         }
	    	
	    }
	    public User create(User user) throws UserException {
	        try {
	            //save user object in the database
	        	begin();
	        	getSession().save(user);
	        	commit();
	        	close();
	        	
	        	return user;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
	    }

	    public void delete(User user) throws UserException {
	    	 try {
	             //save user object in the database
	         	begin();
	         	getSession().delete(user);
	         	commit();
	         	
	         } catch (HibernateException e) {
	             rollback();
	             //throw new AdException("Could not create user " + username, e);
	             throw new UserException("Exception while deleting user: " + e.getMessage());
	         }
	    }
}
