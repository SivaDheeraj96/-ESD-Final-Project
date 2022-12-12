package com.esd.app.controller;

import java.net.http.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.esd.app.dao.BusRouteDAO;
import com.esd.app.dao.BusTripDAO;
import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;
import com.esd.app.validator.BusRouteValidator;
import com.esd.app.validator.BusTripValidator;

@Controller
public class TripController {

	@Autowired
	private BusTripValidator busTripValidator;
	
	@Autowired
	private BusTripDAO busTripDAO;
	
	@Autowired
	private BusRouteDAO busRouteDAO;
	
	@GetMapping("/admin/trip/add")
	public String showRouteAdminPage(Model model) {
		try {
			model.addAttribute("routes", busRouteDAO.getAllRoutes());
		} catch (RouteException e) {
			model.addAttribute("tripError", "Couldn't get all routes");
			e.printStackTrace();
		}
		return "admin/add-bus-trip";
	}
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	
	@PostMapping("/admin/trip/add")
	public String handleRouteAdminPage(@ModelAttribute BusTrip _trip, BindingResult result, SessionStatus status, Model model, HttpServletRequest req) {
		BusTrip trip = new BusTrip();
		
		String routeId = (String)req.getParameter("busRoute");
		BusRoute route;
		try {
			route = busRouteDAO.get(routeId);
			System.out.println(route.getDestinationName());
			trip.setBusRoute(route);
			trip.setTripDate(format.parse(req.getParameter("tripDate")));
			trip.setPrice(Integer.parseInt(req.getParameter("price")));
		} catch (RouteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			model.addAttribute("tripError", "Error while parsing date. Please try again");
			return "admin/add-bus-trip";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("tripError", "Error while parsing date. Please try again");
			return "admin/add-bus-trip";
		}
		
//		String routeId = (String)req.getParameter("busRoute");
//		try {
//			System.out.println("routeid:"+routeId);
//			BusRoute route = busRouteDAO.get(routeId);
//			System.out.println(route.getDestinationName());
//			trip.setBusRoute(route);
//			trip.setTripDate(format.parse(req.getParameter("date")));
//		} catch (RouteException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		    model.addAttribute("tripError", "Error while parsing date. Please try again");
//			return "admin/add-bus-trip";
//		}
		
		try {
			BusTrip saveTrip = busTripDAO.get(trip.getBusRoute(), trip.getTripDate());
			if(saveTrip != null) {
				System.out.println("Trip found");
				model.addAttribute("tripError", "Trip already exists");
				return "admin/add-bus-trip";
			}
		} catch (RouteException e) {
			e.printStackTrace();
			model.addAttribute("tripError", "Error while fetching the route trips. Please try again");
			return "admin/add-bus-trip";
		}
		try {
			busTripDAO.create(trip);
		} catch (RouteException e) {
			model.addAttribute("tripError", "Error while creating the route. Please try again");
			e.printStackTrace();
		}
		try {
			model.addAttribute("routes", busRouteDAO.getAllRoutes());
		} catch (RouteException e) {
			model.addAttribute("tripError", "Couldn't get all routes");
			e.printStackTrace();
		}
		model.addAttribute("created","Successfully added Trip");
		System.out.println("Trip created");
		return "admin/add-bus-trip";
	}
	
	@GetMapping("/admin/trip/view")
	public String showTripAdminPage(Model model) {
		try {
			model.addAttribute("trips", busTripDAO.getAllTrips());
		} catch (RouteException e) {
			model.addAttribute("tripError", "Couldn't get all routes");
			e.printStackTrace();
		}
		return "admin/view-bus-trip";
	}
	
	@DeleteMapping("/admin/trip")
	public String deleteTrip(HttpServletRequest req, Model model, SessionStatus status) {
		System.out.println("inside delete");
		try {
			String tripId = req.getParameter("tripId");
			System.out.println("tripid:"+tripId);
			BusTrip saveTrip = busTripDAO.get(tripId);
			busTripDAO.delete(saveTrip);
			model.addAttribute("trips", busTripDAO.getAllTrips());
		} catch (RouteException e) {
			model.addAttribute("error", "Unable to delete as there are bookings on the trip");
			e.printStackTrace();
		}
		System.out.println("deleted");
		status.setComplete();
		
		return "admin/view-bus-trip";
	}
}
