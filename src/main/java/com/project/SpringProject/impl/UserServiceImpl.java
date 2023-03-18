package com.project.SpringProject.impl;


import com.project.SpringProject.entity.Users;
import com.project.SpringProject.repository.UsersRepository;
import com.project.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private  final UsersRepository usersRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser=usersRepository.findByEmail(s);
        if(myUser!=null){

            User secUser=new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }



    @Override
    public Users getUserById(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public void deleteUser(Users user) {
        usersRepository.delete(user);
    }

    @Override
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }


}
