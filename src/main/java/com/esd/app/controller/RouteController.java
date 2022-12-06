package com.esd.app.controller;

import java.net.http.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.esd.app.dao.BusRouteDAO;
import com.esd.app.exception.RouteException;
import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.User;
import com.esd.app.validator.BusRouteValidator;

@Controller
public class RouteController {

	@Autowired
	private BusRouteValidator busRouteValidator;
	
	@Autowired
	private BusRouteDAO busRouteDAO;
	
	@GetMapping("/admin/route/add")
	public String showAdminPage(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		System.out.println("loggedin User Id:"+currentUser.getId());
		return "admin/add-bus-route";
	}
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	
	@PostMapping("/admin/route/add")
	public String handleAdminPage(@ModelAttribute BusRoute route, BindingResult result, SessionStatus status, Model model, HttpServletRequest req) {
		busRouteValidator.validate(route, result);
		if(result.hasErrors()) {
			return "admin/add-bus-route";
		}
		try {
			BusRoute savedRoute = busRouteDAO.get(route.getSourceName(), route.getDestinationName());
			if(savedRoute != null) {
				System.out.println("Route found");
				model.addAttribute("routeExists", "Route already exists");
				return "admin/add-bus-route";
			}
		} catch (RouteException e) {
			e.printStackTrace();
			model.addAttribute("routeError", "Error while fetching the route trips. Please try again");
			return "admin/add-bus-route";
		}
		try {
			busRouteDAO.create(route);
		} catch (RouteException e) {
			// TODO Auto-generated catch block
			model.addAttribute("routeError", "Error while creating the route. Please try again");
			e.printStackTrace();
		}
		model.addAttribute("created","Successfully added Route");
		System.out.println("Route created");
		return "admin/add-bus-route";
	}

	@GetMapping("/admin/route/view")
	public String showViewRoute( Model model) {
		try {
			model.addAttribute("routes", busRouteDAO.getAllRoutes());
		} catch (RouteException e) {
			model.addAttribute("tripError", "Couldn't get all routes");
			e.printStackTrace();
		}
		return "admin/view-bus-route";
	}
	
	@RequestMapping(value="/admin/route/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRoute( Model model, HttpServletRequest req) {
		try {
			String routeId = req.getParameter("routeId");
			BusRoute route = busRouteDAO.get(routeId);
			System.out.println("delete reqyest for :"+routeId);
			if(route == null) {
				System.out.println("Route not found:"+routeId);
				model.addAttribute("tripError", "Route doesn't exists");
				return "admin/view-bus-route";
			}
			System.out.println("Deleting route:"+routeId);
			busRouteDAO.delete(route);
			model.addAttribute("routes", busRouteDAO.getAllRoutes());
		} catch (RouteException e) {
			model.addAttribute("tripError", "Couldn't get all routes");
			e.printStackTrace();
		}
		return "admin/view-bus-route";
	}
}
