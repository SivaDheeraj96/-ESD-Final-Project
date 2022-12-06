package com.esd.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.esd.app.dao.BusRouteDAO;
import com.esd.app.dao.BusTripDAO;
import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;
import com.esd.app.pojo.User;

@Controller
public class BookingController {
	
	@Autowired
	private BusTripDAO busTripDAO;

	@GetMapping("/booking/confirmation")
	public String showConfirmationPage(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		System.out.println("loggedin User Id:"+currentUser.getId());
		model.addAttribute("user", currentUser);
		String tripId =req.getParameter("tripId");
		try {
			
			BusTrip trip = busTripDAO.get(tripId);
			BusRoute route = trip.getBusRoute();
			model.addAttribute("route", route);
			model.addAttribute("trip", trip);
			session.setAttribute("trip", trip);
			session.setAttribute("route", route);
		} catch (RouteException e) {
			System.out.println("unable to get the trip for:"+tripId);
			e.printStackTrace();
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
		if(trip == null || route == null ) {
			return "search";
		}
		model.addAttribute("route", route);
		model.addAttribute("trip", trip);
		session.removeAttribute("trip");
		session.removeAttribute("route");
		return "booking-success";
	}

}
