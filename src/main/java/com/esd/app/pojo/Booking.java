package com.esd.app.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name = "Booking")
public class Booking {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String bookingId;
	
	@OneToOne
	private BusTrip trip;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	private Date bookingDate;
	
	private Integer count;
	
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public BusTrip getTrip() {
		return trip;
	}

	public void setTrip(BusTrip trip) {
		this.trip = trip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStringBookingDate() {
		return DATE_FORMAT.format(bookingDate);
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
}
