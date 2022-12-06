package com.esd.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;

@Component
public class BusRouteDAO extends DAO{
	
	public BusRouteDAO() {
		
	}
	
	public List<BusRoute> getAllRoutes() throws RouteException {
   	 try {
           
        	begin();
        	Query q = getSession().createQuery("from BusRoute");
        	System.out.println(q.getResultList().size());
        	List<BusRoute> route =  q.getResultList();
        	commit();
        	return route;
        	
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get All Routes", e);
        }
   	
   }
	public BusRoute get(String id) throws RouteException {
	   	 try {
	           
	        	begin();
	        	Query q = getSession().createQuery("from BusRoute where routeId= :id");
	        	q.setParameter("id", id);
				int size = q.getResultList().size();
	        	System.out.println("size: " + size);
	        	BusRoute route = size  == 0? null:(BusRoute) q.getSingleResult();
	        	commit();
	        	return route;
	        	
	        } catch (HibernateException e) {
	            rollback();
	            throw new RouteException("Could not get route :" + id, e);
	        }
	   	
	   }
	public BusRoute get(String source, String destination) throws RouteException {
   	 try {
        	begin();
        	Query q = getSession().createQuery("from BusRoute where sourceName= :source and destinationName= :destination");
        	q.setParameter("source", source);
			q.setParameter("destination", destination);
			int size = q.getResultList().size();
        	System.out.println("size: " + size);
        	BusRoute route = size  == 0? null:(BusRoute) q.getSingleResult();
        	commit();
        	return route;
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get route between source:" + source +" destination:"+destination, e);
        }
   }
   public BusRoute create(BusRoute route) throws RouteException {
       try {
           //save user route in the database
       	begin();
       	getSession().save(route);
       	commit();
       	close();
       	
       	return route;
       } catch (HibernateException e) {
           rollback();
           throw new RouteException("Exception while creating route between source:" + route.getSourceName() + " destination:"+ route.getDestinationName() + e.getMessage());
       }
   }

   public void delete(BusRoute route) throws RouteException {
   	 try {
            //delete route object in the database
        	begin();
        	getSession().delete(route);
        	commit();
        	
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Exception while deleting route between source:" + route.getSourceName() +" destination:"+ route.getDestinationName() + e.getMessage());
        }
   }

}
