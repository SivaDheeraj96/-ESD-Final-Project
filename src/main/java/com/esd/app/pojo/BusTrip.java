package com.esd.app.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "BusTrip")
public class BusTrip {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-mmm-yyy"); 
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String tripId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="routeId")
	private BusRoute busRoute;

	@Column(name = "tripDate", nullable = false)
	private Date tripDate;

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
	
}
