package com.esd.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;

@Component
public class BusTripDAO extends DAO{
	
	public BusTripDAO() {
		
	}
	
	public List<BusTrip> get(BusRoute route) throws RouteException {
   	 try {
        	begin();
        	Query q = getSession().createQuery("from BusTrip where routeId= :routeId");
        	q.setParameter("routeId", route.getRouteId());
        	System.out.println(q.getResultList().size());
        	List<BusTrip> trips = (List<BusTrip>)q.getResultList();
        	commit();
        	return trips;
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get trip for routeId:" + route.getRouteId(), e);
        }
   	
   }
	
	public BusTrip get(BusRoute route, Date date) throws RouteException {
   	 try {
        	begin();
        	Query q = getSession().createQuery("from BusTrip where routeId= :routeId and tripDate = :date");
        	q.setParameter("routeId", route.getRouteId());
        	q.setParameter("date", date);
        	int size = q.getResultList().size();
        	BusTrip trip = size == 0 ? null: (BusTrip)q.getSingleResult();
        	commit();
        	return trip;
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get trip for routeId:" + route.getRouteId(), e);
        }
   	
   }
	
	public BusTrip get(String id) throws RouteException {
	   	 try {
	        	begin();
	        	Query q = getSession().createQuery("from BusTrip where tripId= :tripId");
	        	q.setParameter("tripId", id);
	        	int size = q.getResultList().size();
	        	BusTrip trip = size == 0 ? null: (BusTrip)q.getSingleResult();
	        	commit();
	        	return trip;
	        } catch (HibernateException e) {
	            rollback();
	            throw new RouteException("Could not get trip for tripId:" + id, e);
	        }
	   	
	   }
   public BusTrip create(BusTrip trip) throws RouteException {
	   try {
	       //save user trip in the database
	   	begin();
	   	getSession().save(trip);
	   	commit();
	   	close();
	   	return trip;
	   } catch (HibernateException e) {
	       rollback();
	       throw new RouteException("Exception while creating trip:" + trip.getTripId(), e);
	   }
   }

   public void delete(BusTrip trip) throws RouteException {
   	 try {
            //delete trip object in the database
        	begin();
        	getSession().delete(trip);
        	commit();
        	
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Exception while deleting trip:" + trip.getTripId(), e);
        }
   }

}
