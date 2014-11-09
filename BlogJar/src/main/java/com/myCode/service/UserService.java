package com.myCode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myCode.dao.UserDao;
import com.myCode.entity.Users;

@Service
public class UserService implements UserDetailsService{

	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	public void createNewUser(Users user) {
		
		System.out.println("Password : "+user.getPassword());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		
		System.out.println("Hashed Password : "+hashedPassword);
		user.setPassword(hashedPassword);
		user.setRole(2);
		userDao.createNewUser(user);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Users user = userDao.getUserDetails(username);
		System.out.println(username);
		User userdetail = new User(user.getUsername(),user.getPassword(),true,true,true,true,getAuthorities(user.getRole()));
		return userdetail;
	}
	
	private List<GrantedAuthority> getAuthorities(Integer role)
	{
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if(role.intValue()==1){
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else if(role.intValue()==2)
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return authList;
	}
	
}
