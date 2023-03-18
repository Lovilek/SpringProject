package com.project.SpringProject.impl;

import com.project.SpringProject.entity.Roles;
import com.project.SpringProject.entity.Users;
import com.project.SpringProject.repository.RoleRepository;
import com.project.SpringProject.repository.UsersRepository;
import com.project.SpringProject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    private  final UsersRepository usersRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;



    @Override
    public Users addUser(Users u) {
        Users checkUser=usersRepository.findByEmail(u.getEmail());
        if(checkUser==null){
            Roles role= roleRepository.findByRole("ROLE_USER");
            if (role!=null){
                ArrayList<Roles> roles=new ArrayList<>();
                roles.add(role);
                u.setRoles(roles);
                u.setPassword(passwordEncoder.encode(u.getPassword()));

                return usersRepository.save(u);
            }

        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
