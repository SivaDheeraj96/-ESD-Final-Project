package com.esd.app.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.esd.app.pojo.User;

@Component
public class AppSecurity implements HandlerInterceptor {

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		System.out.println("interceptor for url:"+url);
		if(url.contains("/login")|| url.contains("/signup") || url.contains("/static") || url.contains("/booking/email")) {
			System.out.println("returned bypassed");
			return true;
		}
		else {
			HttpSession session = request.getSession(false);
			if(session == null) {
				System.out.println("no session found");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return false;
			}
			else {
				System.out.println("session is present :"+session.getId());
				User currentUser = (User)session.getAttribute("user");
				if(currentUser == null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
					dispatcher.forward(request, response);
				}
				else {
					System.out.println("current user id:"+currentUser.getId());	
				}
				
				return true;
			}
		}
   }
}