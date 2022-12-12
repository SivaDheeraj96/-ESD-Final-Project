package com.esd.app.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;

import com.dheeraj.MailUtil;
import com.esd.app.dao.BookingDAO;
import com.esd.app.dao.BusRouteDAO;
import com.esd.app.dao.BusTripDAO;
import com.esd.app.exception.RouteException;
import com.esd.app.pojo.Booking;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;
import com.esd.app.pojo.User;
import com.esd.app.util.EmailSender;
import com.esd.app.util.EmailUtil;
import com.esd.app.util.TicketPDF;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;


@Controller
public class BookingController {
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	private BusTripDAO busTripDAO;
	
	@Autowired
	private BookingDAO bookingDAO;


	@GetMapping("/booking/view")
	public String showBookings(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		System.out.println("loggedin User Id:"+currentUser.getId());
		model.addAttribute("user", currentUser);
		List<Booking> bookings;
		try {
			bookings = bookingDAO.get(currentUser);
			model.addAttribute("bookings", bookings);
		} catch (RouteException e) {
			e.printStackTrace();
			model.addAttribute("error","Unable to get the bookings, Please try again");
			return "search";
		}
		return "bookings";
	}
	
	@GetMapping("/booking/confirmation")
	public String showConfirmationPage(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		System.out.println("loggedin User Id:"+currentUser.getId());
		model.addAttribute("user", currentUser);
		String tripId =req.getParameter("tripId");
		Integer count =Integer.parseInt(req.getParameter("count"));
		if(count>5) {
			model.addAttribute("error","Can't book more than 5 tickets per transaction");
			return "search";
		}
		try {
			
			BusTrip trip = busTripDAO.get(tripId);
			BusRoute route = trip.getBusRoute();
			model.addAttribute("route", route);
			model.addAttribute("trip", trip);
			model.addAttribute("count",count);
			session.setAttribute("trip", trip);
			session.setAttribute("route", route);
			session.setAttribute("count", count);
		} catch (RouteException e) {
			System.out.println("unable to get the trip for:"+tripId);
			e.printStackTrace();
			model.addAttribute("error","Unable to get the trip detail, Please try again");
			return "search";
		}
		return "booking-confirmation";
	}
	@GetMapping("/booking/success")
	public String showSuccessPage(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		System.out.println("loggedin User Id:"+currentUser.getId());
		model.addAttribute("user", currentUser);
		BusTrip trip= (BusTrip)session.getAttribute("trip");
		BusRoute route= (BusRoute)session.getAttribute("route");
		Integer count= (Integer)session.getAttribute("count");
		if(trip == null || route == null ) {
			return "search";
		}
		Booking booking = new Booking();
		booking.setTrip(trip);
		booking.setUser(currentUser);
		booking.setBookingDate(new Date());
		booking.setCount(count);
		try {
			bookingDAO.create(booking);
		} catch (RouteException e) {
			System.out.println("unable to create a booking");
			e.printStackTrace();
			model.addAttribute("error","Unable to book the trip, Please try again");
			return "search";
		}
		model.addAttribute("route", route);
		model.addAttribute("trip", trip);
		
		return "booking-success";
	}
	@Autowired
	private EmailSender emailSender;
	@GetMapping("/booking/email")
	public String sendEmail(HttpServletRequest req, Model model) {
		System.out.println("inside mapping");
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		BusTrip trip= (BusTrip)session.getAttribute("trip");
		Integer count= (Integer)session.getAttribute("count");
		List<String[]> info = new ArrayList();
		info.add(new String[] {"First Name",currentUser.getFirstName()});
		info.add(new String[] {"Last Name",currentUser.getLastName()});
		info.add(new String[] {"Email",currentUser.getEmail()});
		info.add(new String[] {"Source Location",trip.getBusRoute().getSourceName()});
		info.add(new String[] {"Destination Location",trip.getBusRoute().getDestinationName()});
		info.add(new String[] {"Trave Date",trip.getStringTripDate()});
		info.add(new String[] {"Number of Tickets",String.valueOf(count)});
		info.add(new String[] {"Ticket Price","USD "+String.valueOf(trip.getPrice())});
		info.add(new String[] {"Total Price","USD "+String.valueOf(count * trip.getPrice())});
		try {
			Document doc = new Document();
			File f = new File("ticket.pdf");
			if(!f.exists()) {
				 try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} 
			OutputStream os = new FileOutputStream("ticket.pdf");
			PdfWriter.getInstance(doc,os);
			TicketPDF.buildTicket(doc, info);
			emailSender.sendingMail(currentUser.getEmail(), "Ticket Information", "Please find the ticket pdf ", "ticket.pdf");
		} catch (MessagingException e) {
			System.out.println("error in util");
			e.printStackTrace();
			return "false";
		} catch (FileNotFoundException e) {
					e.printStackTrace();
					return "false";
		} catch (DocumentException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	@GetMapping("/booking/print")
	public View getPDF(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		BusTrip trip= (BusTrip)session.getAttribute("trip");
		Integer count= (Integer)session.getAttribute("count");
		List<String[]> info = new ArrayList();
		info.add(new String[] {"First Name",currentUser.getFirstName()});
		info.add(new String[] {"Last Name",currentUser.getLastName()});
		info.add(new String[] {"Email",currentUser.getEmail()});
		info.add(new String[] {"Source Location",trip.getBusRoute().getSourceName()});
		info.add(new String[] {"Destination Location",trip.getBusRoute().getDestinationName()});
		info.add(new String[] {"Trave Date",trip.getStringTripDate()});
		info.add(new String[] {"Number of Tickets",String.valueOf(count)});
		info.add(new String[] {"Ticket Price","USD "+String.valueOf(trip.getPrice())});
		info.add(new String[] {"Total Price","USD "+String.valueOf(count * trip.getPrice())});
		
		req.setAttribute("info", info);
		
		return new TicketPDF();
	}
	
	
	

}
