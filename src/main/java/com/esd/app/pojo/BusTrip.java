package com.esd.app.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esd.app.dao.BookingDAO;
import com.esd.app.exception.RouteException;

@Entity
@Component
@Table(name = "BusTrip")
public class BusTrip {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy"); 
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String tripId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="routeId")
	private BusRoute busRoute;

	@Column(name = "tripDate", nullable = false)
	private Date tripDate;
	
	@Column(name = "price", nullable = false)
	private int price;

	@Basic(fetch = FetchType.EAGER)
	private int bookingCount;
	
	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public BusRoute getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public String getStringTripDate() {
		return DATE_FORMAT.format(tripDate);
	}
	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Basic(fetch = FetchType.EAGER)
	@Formula(value = "(select count(b.bookingId) from Booking b where trip_tripId=tripId)")
	public int getBookingCount() {
		return this.bookingCount;
	}
	
	
}
