package com.project.SpringProject.service;

import com.project.SpringProject.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);
    List<Users> findAllUsers();

    Users getUserById(Long id);
    void deleteUser(Users user);
    Users saveUser(Users user);

}
