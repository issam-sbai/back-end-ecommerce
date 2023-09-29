package com.example.ecomraectspring.security.service;



import com.example.ecomraectspring.security.dao2.IRoleDao;
import com.example.ecomraectspring.security.dao2.IUserDao;
import com.example.ecomraectspring.security.entity.Role;
import com.example.ecomraectspring.security.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImp implements AccountService {

    private IUserDao userDao;
    private IRoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    @Override
    public User addNewUser(String username, String password, String confirmPassword, String prenom) {
        User appUser = userDao.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("this user already exist");
        if (!password.equals(confirmPassword)) throw new RuntimeException("password not match");
        appUser = User.builder()
                .username(username)
                .prenom(prenom)
                .password(passwordEncoder.encode(password))
                .build();
        User saveAppUser = userDao.save(appUser);

        return saveAppUser;
    }

    @Override
    public Role addNewRole(String role ) {
        Role appRole = roleDao.findRoleByNom(role);

        if (appRole!= null)throw  new RuntimeException("this role already exist");
        appRole = Role.builder().nom(role).build();
        return roleDao.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, int idrole) {
        User appUser = userDao.findByUsername(username);
        Role appRole = roleDao.findById(idrole).get();
        appUser.getRoles().add(appRole);
        //appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, int idrole) {
        User appUser = userDao.findByUsername(username);
        Role appRole = roleDao.findById(idrole).get();
        appUser.getRoles().remove(appRole);
        //appUserRepository.save(appUser);

    }

    @Override
    public User loadUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    //@Override
    //public List<User> serchByEmail(String email) {
    //    return userDao;
    //}

    //@Override
    //public User getByUserId(String id) {
    //    return userDao.getByUserId(id);
    //}

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    //@Override
    //public void deleteByRole(String id) {
    //    roleDao.deleteByRole(id);

    //}

    public List<Role> getAllRole() {
        return roleDao.findAll();
    }




}