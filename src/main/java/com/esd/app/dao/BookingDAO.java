package com.esd.app.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.app.exception.RouteException;
import com.esd.app.pojo.Booking;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;
import com.esd.app.pojo.User;

@Component
public class BookingDAO extends DAO{
	
	public BookingDAO() {
		
	}
	
	public List<Booking> get(User user) throws RouteException {
   	 try {
        	begin();
        	Query q = getSession().createQuery("from Booking where userId= :userId");
        	q.setParameter("userId", user.getId());
        	System.out.println(q.getResultList().size());
        	List<Booking> bookings = (List<Booking>)q.getResultList();
        	commit();
        	return bookings;
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get trip for userId:" + user.getId(), e);
        }
   	
   }
	
	public Booking get(User user, BusTrip trip) throws RouteException {
   	 try {
        	begin();
        	Query q = getSession().createQuery("from Booking where userId= :userId and trip_tripId = :tripId");
        	q.setParameter("userId", user.getId());
        	q.setParameter("tripId", trip.getTripId());
        	int size = q.getResultList().size();
        	Booking booking = size == 0 ? null: (Booking)q.getSingleResult();
        	commit();
        	return booking;
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Could not get trip for trip:" + trip.getTripId(), e);
        }
   	
   }
	
	public Booking get(String id) throws RouteException {
	   	 try {
	        	begin();
	        	Query q = getSession().createQuery("from Booking where bookingId= :bookingId");
	        	q.setParameter("bookingId", id);
	        	int size = q.getResultList().size();
	        	Booking booking = size == 0 ? null: (Booking)q.getSingleResult();
	        	commit();
	        	return booking;
	        } catch (HibernateException e) {
	            rollback();
	            throw new RouteException("Could not get trip for tripId:" + id, e);
	        }
	   	
	   }
   public Booking create(Booking booking) throws RouteException {
	   try {
	       //save user trip in the database
	   	begin();
	   	getSession().save(booking);
	   	commit();
	   	close();
	   	return booking;
	   } catch (HibernateException e) {
	       rollback();
	       throw new RouteException("Exception while creating trip:" + booking.getBookingId(), e);
	   }
   }

   public void delete(Booking booking) throws RouteException {
   	 try {
            //delete trip object in the database
        	begin();
        	getSession().delete(booking);
        	commit();
        	
        } catch (HibernateException e) {
            rollback();
            throw new RouteException("Exception while deleting trip:" + booking.getBookingId(), e);
        }
   }
   
   public Long getCount(String id) throws RouteException {
	   try {
           //delete trip object in the database
       	begin();
       	CriteriaBuilder builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<Long> crit = builder.createQuery(Long.class);
    	crit.select(builder.count(crit.from(BusTrip.class)));
    	Query<Long> query = getSession().createQuery(crit);
    	Long count = query.getResultList().get(0);
       	commit();
       	return count; 
       } catch (HibernateException e) {
           rollback();
           throw new RouteException("Exception while getting booking count", e);
       }
   }

}
