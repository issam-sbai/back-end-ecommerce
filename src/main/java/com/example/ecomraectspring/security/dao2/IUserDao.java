package com.example.ecomraectspring.security.dao2;


import com.example.ecomraectspring.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;





public interface IUserDao extends JpaRepository<User, Integer> {
	
	
	public User findByUsername(String username);

}
