package com.example.ecomraectspring.security.dao2;

import com.example.ecomraectspring.security.entity.Role;
import com.example.ecomraectspring.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;





public interface IRoleDao extends JpaRepository<Role, Integer> {

    public Role findRoleByNom(String namRole);

}
