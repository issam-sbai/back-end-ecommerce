package com.example.ecomraectspring.security.service;


import com.example.ecomraectspring.security.entity.Role;
import com.example.ecomraectspring.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;





@Service
public class CustemUserDetailService implements UserDetailsService {
	
	@Autowired
	IuserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userService.chercherparUsername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorities=getGrantedAuthority(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
		
		

	}

	private List<GrantedAuthority> getGrantedAuthority(User u) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles = u.getRoles();
		for (Role r : roles) {
			if (r != null && r.getNom() != null && !r.getNom().isEmpty()) {
				authorities.add(new SimpleGrantedAuthority(r.getNom()));
			}
		}
		return authorities;
	}







}
