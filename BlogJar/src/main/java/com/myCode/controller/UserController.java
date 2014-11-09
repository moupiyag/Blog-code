package com.myCode.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myCode.entity.Users;
import com.myCode.service.UserService;

@Controller
public class UserController{
	
	HttpSession session;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value = "/test", method= RequestMethod.GET)
	public ModelAndView showTestPage()
	{
		ModelAndView model = new ModelAndView("TestPage");
		
		//userservice.newUser();
		//model.getModelMap().addAttribute("userList", users);
		model.getModelMap().addAttribute("msg1", "Hello Test");
		model.addObject("msg2","HelloWorld");
		
		return model;
	}
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public ModelAndView showLoginPage()
	{
		ModelAndView model = new ModelAndView("loginPage");
		model.getModelMap().addAttribute("user" ,new Users());
		//model.addObject("user" ,new Users());
		//userservice.newUser();
		//model.getModelMap().addAttribute("userList", users);
		//model.getModelMap().addAttribute("msg1", "Hello Test");
		//model.addObject("msg2","HelloWorld");
		
		return model;
	}
	
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public ModelAndView doLogin(@ModelAttribute("user") Users user,HttpServletRequest request,Principal principal)
	{
		ModelAndView model = new ModelAndView("TestPage");
		
		session = request.getSession();
		if (session == null)
			System.out.println("Session in null");
		
		session.setAttribute("currusername", principal.getName());
		//session.setAttribute("curruserrole", user.getRole());
		//System.out.println("Username :" +user.getUsername());
		
		//userservice.newUser(user);
		//model.getModelMap().addAttribute("userList", users);
		//model.getModelMap().addAttribute("msg1", "User created with first name :"+user.getFirstName()+"and last name:"+user.getLastName());
		model.getModelMap().addAttribute("msg1", "User created with first name :"+(String)session.getAttribute("currusername"));
		model.addObject("msg2","Login successful");
		
		return model;
	}
	
	@RequestMapping(value = "/registration.do", method=RequestMethod.GET)
	public ModelAndView showRegistrationPage()
	{
		ModelAndView model = new ModelAndView("signIn");
		model.getModelMap().addAttribute("user" ,new Users());
		
		return model;
	}
	
	@RequestMapping(value="/newuser.do", method=RequestMethod.POST)
	public ModelAndView newUserRegistration(@ModelAttribute("user") Users user,HttpServletRequest request,Principal principal)
	{
		userservice.createNewUser(user);
		ModelAndView model = doLogin(user,request,principal);
		return model;
	}
}
