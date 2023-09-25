package com.example.ecomraectspring.controller;


import com.example.ecomraectspring.security.entity.AuthentificationRequest;
import com.example.ecomraectspring.security.entity.AuthentificationResponse;
import com.example.ecomraectspring.security.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class AuthentificationController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserDetailsService custemUserDetailsService;
	
	
	@Autowired
	private jwtUtil jwtokenUtil;
	
	/*@GetMapping("/test22")
	public String test22()
	{
		return "Bonjour";
	}*/
	
	@RequestMapping(value="/loginUserJwt" ,method =RequestMethod.POST)
	public AuthentificationResponse authenticate(@RequestBody AuthentificationRequest authentificationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getUsername(), authentificationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("incorrect username ou password",e);
		}
		
		final UserDetails userdetails=custemUserDetailsService.loadUserByUsername(authentificationRequest.getUsername());
		final String jwt=jwtokenUtil.generateToken(userdetails);
		
		
		return new AuthentificationResponse(jwt);
	}
	
	
	
}
