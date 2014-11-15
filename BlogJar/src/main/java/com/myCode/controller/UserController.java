package com.myCode.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myCode.entity.Users;
import com.myCode.service.UserService;
import com.myCode.constant.ViewManagerConstant;

@Controller
public class UserController{
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	HttpSession session = null;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value = "/test", method= RequestMethod.GET)
	public ModelAndView showTestPage()
	{
		logger.info("In showTestPage function");
		ModelAndView model = new ModelAndView(ViewManagerConstant.TESTPAGE);
		
		model.getModelMap().addAttribute("msg1", "Hello Test");
		model.addObject("msg2","HelloWorld");
		
		return model;
	}
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public ModelAndView showLoginPage()
	{
		logger.info("In showLoginPage function");
		ModelAndView model = new ModelAndView(ViewManagerConstant.LOGIN);
		model.getModelMap().addAttribute("user" ,new Users());
		
		return model;
	}
	
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public ModelAndView doLogin(@ModelAttribute("user") Users user,HttpServletRequest request,Principal principal)
	{
		logger.info("In doLogin function");
		ModelAndView model = new ModelAndView(ViewManagerConstant.TESTPAGE);
		try{
			logger.info("1");
			session = request.getSession();
			logger.info("2");
			if (session == null)
				System.out.println("Session in null");
			logger.info("3");
			logger.info("Current user : "+principal.getName());
			session.setAttribute("currusername", principal.getName());
			model.getModelMap().addAttribute("msg1", "User created with first name :"+(String)session.getAttribute("currusername"));
			model.addObject("msg2","Login successful");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getCause());
		}
		
		return model;
	}
	
	@RequestMapping(value = "/registration.do", method=RequestMethod.GET)
	public ModelAndView showRegistrationPage()
	{
		ModelAndView model = new ModelAndView(ViewManagerConstant.SIGNIN);
		model.getModelMap().addAttribute("user" ,new Users());
		
		return model;
	}
	
	@RequestMapping(value="/newuser.do", method=RequestMethod.POST)
	public ModelAndView newUserRegistration(@ModelAttribute("user") Users user,HttpServletRequest request,Principal principal)
	{
		userservice.createNewUser(user);
		logger.info("newUserRegistration : User name : "+user.getUsername()+"  Password : "+user.getPassword());
		ModelAndView model = doLogin(user,request,principal);
		return model;
	}
}
