package com.esd.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.esd.app.dao.BusRouteDAO;
import com.esd.app.dao.BusTripDAO;
import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;
import com.esd.app.pojo.User;
import com.esd.app.validator.BusRouteValidator;

@Controller
public class SearchController {

	@Autowired
	private BusRouteValidator busRouteValidator;
	
	@Autowired
	private BusRouteDAO busRouteDAO;
	
	@Autowired
	private BusTripDAO busTripDAO;
	
	@GetMapping("/search")
	public String showSearch(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("no session found");
		}
		else {
			User currentUser = (User)session.getAttribute("user");
			System.out.println("current user id:"+currentUser.getId());
		}
		return "search";
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute BusRoute route, BindingResult result, SessionStatus status, Model model) {
		busRouteValidator.validate(route, result);
		if(result.hasErrors()) {
			return "search";
		}
		BusRoute savedRoute;
		try {
			savedRoute = busRouteDAO.get(route.getSourceName(), route.getDestinationName());
			if(savedRoute == null) {
				System.out.println("Route not found");
				model.addAttribute("routeError", "There are not buses in the given route, Please try different places");
				return "search";
			}
		} catch (RouteException e) {
			e.printStackTrace();
			model.addAttribute("routeError", "Error while fetching the route trips. Please try again");
			return "search";
		} 
		status.setComplete();
		System.out.println("found route!!!");
		model.addAttribute("route", savedRoute);
		System.out.println("added route to model");
		try {
			List<BusTrip> trips = busTripDAO.get(savedRoute);
			model.addAttribute("trips",trips);
			System.out.println("added trips to model");
		} catch (RouteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		model.addAttribute("tripCount", route.getDates().size());
		return "search-result";
	}
}
