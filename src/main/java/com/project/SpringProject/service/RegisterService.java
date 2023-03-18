package com.project.SpringProject.service;

import com.project.SpringProject.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RegisterService extends UserDetailsService {
    Users addUser(Users u);

}
