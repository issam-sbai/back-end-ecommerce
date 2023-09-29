package com.example.ecomraectspring.security.service;





import com.example.ecomraectspring.security.entity.Role;
import com.example.ecomraectspring.security.entity.User;

import java.util.List;

public interface AccountService  {


    User addNewUser(String username, String password, String confirmPassword, String prenom);

    Role addNewRole(String role);


    void addRoleToUser(String username, int idrole);

    void removeRoleFromUser(String username, int idrole);

    User loadUserByUsername(String username);

    List<User> getAllUser();

    //List<User> getAllUser();
    //List<User> serchByEmail(String email);
    //User getByUserId(String id);
    User findByUsername(String username);

   // void deleteByRole(String id);
    public List<Role> getAllRole();




}