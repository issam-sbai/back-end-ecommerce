package com.example.ecomraectspring.controller;


import com.example.ecomraectspring.entity.Categorie;
import com.example.ecomraectspring.entity.Produit;
import com.example.ecomraectspring.security.dao2.IRoleDao;
import com.example.ecomraectspring.security.dao2.IUserDao;
import com.example.ecomraectspring.security.entity.AuthentificationRequest;
import com.example.ecomraectspring.security.entity.AuthentificationResponse;
import com.example.ecomraectspring.security.entity.Role;
import com.example.ecomraectspring.security.entity.User;
import com.example.ecomraectspring.security.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")
public class AuthentificationController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService custemUserDetailsService;

	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;
	
	
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
		System.out.println(authentificationRequest.getPassword());
		try {
			System.out.println("ok");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getUsername(), authentificationRequest.getPassword()));
			System.out.println("ok");
		} catch (BadCredentialsException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("incorrect username ou password", e);
		}

		System.out.println("ok");
		final UserDetails userdetails=custemUserDetailsService.loadUserByUsername(authentificationRequest.getUsername());
		final String jwt=jwtokenUtil.generateToken(userdetails);
		
		
		return new AuthentificationResponse(jwt);
	}

	@RequestMapping(value="/signUP" ,method =RequestMethod.POST)
	public String addUser(
			@RequestBody Map<String, Object> requestParams
	) {

		String nom = (String) requestParams.get("nom");
		String prenom = (String) requestParams.get("prenom");
		String username = (String) requestParams.get("username");
		String password = (String) requestParams.get("password");
		String confirmPassword = (String) requestParams.get("confirmPassword");

		User appUser = userDao.findByUsername(username);
		if (appUser != null) {
			throw new RuntimeException("This user already exists");
		}

		// Check if the passwords match
		if (!password.equals(confirmPassword)) {
			throw new RuntimeException("Passwords do not match");
		}

		// Encode the password
		String hashedPassword = passwordEncoder.encode(password);

		User u = new User();
		u.setNom(nom);u.setPrenom(prenom);u.setPassword(hashedPassword);u.setUsername(username);
		List<Role> roles=new ArrayList<>();
		roles.add(new Role(1,"ADMIN"));
		u.setRoles(roles);
		userDao.save(u);


		return "sign ok";
	}

	/*@RequestMapping(value="/signUP" ,method =RequestMethod.POST)
	public String registerUser(
			@RequestBody String nom,
			@RequestBody String username,
			@RequestBody String password,
			@RequestBody String confirmPassword,
			@RequestBody String prenom
	) {
		// Check if the user already exists
		System.out.println(username);
		System.out.println(password);
		System.out.println(confirmPassword);
		User appUser = userDao.findByUsername(username);
		if (appUser != null) {
			throw new RuntimeException("This user already exists");
		}

		// Check if the passwords match
		if (!password.equals(confirmPassword)) {
			throw new RuntimeException("Passwords do not match");
		}

		// Encode the password
		String hashedPassword = passwordEncoder.encode(password);

		List<Role> roles = new ArrayList<>();
		// Create and save the user
		appUser = new User();
		appUser.setUsername(username);
		appUser.setNom(nom);
		appUser.setRoles(roles);
		appUser.setPrenom(prenom);
		appUser.setPassword(hashedPassword);  // Use the hashed password here

		userDao.save(appUser);

		Role appRole = roleDao.findRoleByNom("USER"); // Replace roleId with the appropriate value

		if (appRole != null) {
			appUser.getRoles().add(appRole);
			userDao.save(appUser);
		} else {
			return "Role not found";
		}

		return "User registered successfully";
	}*/

	
	
}
