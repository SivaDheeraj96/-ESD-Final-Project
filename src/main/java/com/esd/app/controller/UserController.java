package com.esd.app.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.esd.app.dao.UserDAO;
import com.esd.app.exception.UserException;
import com.esd.app.pojo.User;
import com.esd.app.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/home")
	public String getMessage() {
		System.out.println("here");
		return "user-home";
		
	}
	
	@GetMapping("/profile")
	public String showProfile(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		model.addAttribute("user", currentUser);
		return "profile-view";
		
	}
	
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute User user, BindingResult result, SessionStatus status, Model model,@RequestParam("profile_pic") MultipartFile multipartFile) {
		validator.validate(user, result);
		
		if(result.hasErrors()) {
			return "profile-view";
		}
		System.out.println("email:"+user.getEmail());
		System.out.println("email:"+user.getFirstName());
		System.out.println("email:"+user.getLastName());
		System.out.println("email:"+user.getProfilePic());
		User savedUser;
		try {
			savedUser = userDAO.get(user.getEmail());
			if(savedUser == null ) {
				model.addAttribute("error", "User Not Found");
				return "profile-view";
			}
		} catch (UserException e) {
			e.printStackTrace();
			return "profile-view";
		}
		if(multipartFile != null) {
			try {
				Path picFolder = Paths.get("profile_pics");
				if(!Files.exists(picFolder)) {
					Files.createDirectories(picFolder);
				}
				String fileType=multipartFile.getContentType().split("/")[1];
				InputStream is = multipartFile.getInputStream();
				Path filePath = picFolder.resolve("user_"+savedUser.getId()+"."+fileType);
				savedUser.setProfilePic("user_"+savedUser.getId()+"."+fileType);
	            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			userDAO.update(savedUser);
		} catch (UserException e) {
			e.printStackTrace();
			model.addAttribute("error", "Error while Registering! Please try again");
			return "profile-view";			
		}
		model.addAttribute("success", "Profile Update Successfully");
		status.setComplete();
		return "profile-view";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/profilePic", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE} )
	public @ResponseBody byte[] getProfilePic(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		User currentUser = (User)session.getAttribute("user");
		String path = "profile_pics"+"/"+currentUser.getProfilePic();
		Path picFile = Paths.get("profile_pics"+"/"+currentUser.getProfilePic());
		if(!Files.exists(picFile)) {
			path = "profile_pics"+"/default.jpeg";
		}
//		 ClassPathResource imgFile = new ClassPathResource("/"+picFile.toFile().getAbsolutePath());
		res.setContentType(MediaType.IMAGE_JPEG_VALUE);
		System.out.println("profilepic path:"+path);
		try {
			InputStream in = new FileInputStream(path);
			return in.readAllBytes();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new byte[0];
        
	}
}
