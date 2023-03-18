package com.project.SpringProject.controller;


import com.project.SpringProject.entity.Cars;

import com.project.SpringProject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.project.SpringProject.entity.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.SpringProject.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class CarsController {


    final CarsService carsService;


   final private UserService userService;
   final private RegisterService registerService;

    @GetMapping("/cars")
    @PreAuthorize("isAuthenticated()")

    public String cars(Model model) {
        List<Cars> cars = carsService.findAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("currentUSer",getUserData());
        return "cars";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public String userTable(Model model) {
        List<Users> users = userService.findAllUsers();



        model.addAttribute("users", users);
        model.addAttribute("currentUSer",getUserData());
        return "users";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")

    public String addCar(@RequestParam(name = "model") String model,
                             @RequestParam(name = "value") String value,
                             @RequestParam(name = "price") String price) {
        carsService.addCar(new Cars(null, model, value, price));
        return "redirect:/cars";
    }

    @GetMapping("/addcar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String addC(Model model) {

        model.addAttribute("currentUSer",getUserData());
        return "addcar";
    }

    @GetMapping("/editcar/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")

    public String editcar(Model model, @PathVariable(name = "idshka") Long id) {
        Cars car= carsService.getCar(id);

        model.addAttribute("car", car);
        model.addAttribute("currentUSer",getUserData());
        return "editcar";
    }
    @GetMapping("/edituser/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public String edituser(Model model, @PathVariable(name = "idshka") Long id) {
        if(id!=1 && id!=3){
            Users user = userService.getUserById(id);

            model.addAttribute("user", user);
            model.addAttribute("currentUSer", getUserData());
            return "edituser";
        }else{
            Users user= userService.getUserById(id);

            model.addAttribute("user", user);
            model.addAttribute("currentUSer",getUserData());
            return "detailsuser";

        }
    }
    @GetMapping("/detailscar/{idshka}")
    @PreAuthorize("isAuthenticated()")

    public String detailscar(Model model, @PathVariable(name = "idshka") Long id) {
        Cars car= carsService.getCar(id);

        model.addAttribute("car", car);
        model.addAttribute("currentUSer",getUserData());
        return "detailscar";
    }
    @GetMapping("/detailsuser/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public String detailsuser(Model model, @PathVariable(name = "idshka") Long id) {
        Users user= userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("currentUSer",getUserData());
        return "detailsuser";
    }

    @PostMapping("/saveCar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")

    public String saveC(@RequestParam(name = "id", required = true) Long id,
                        @RequestParam(name = "model", required = true) String model,
                        @RequestParam(name = "value", required = true) String value,
                        @RequestParam(name = "price", required = true) String price) {
        Cars car = carsService.getCar(id);
        if (car != null) {
            car.setModel(model);
            car.setValue(value);
            car.setPrice(price);
            carsService.saveCar(car);
        }
        return "redirect:/cars";
    }
    @PostMapping("/saveUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public String saveU(@RequestParam(name = "id", required = true) Long id,
                        @RequestParam(name = "email", required = true) String email,
                        @RequestParam(name = "fullName", required = true) String fullName) {
        Users user = userService.getUserById(id);
        if (user != null) {
            user.setEmail(email);
            user.setFullName(fullName);
            userService.saveUser(user);
        }
        return "redirect:/users";
    }
    @PostMapping("/deleteCar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")

    public String deleteC(Long id) {
        Cars car = carsService.getCar(id);
        if (car != null) {
            carsService.deleteCar(car);
        }
        return "redirect:/cars";

    }
    @PostMapping("/deleteUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public String deleteU(Long id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(user);
        }
        return "redirect:/users";

    }


    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("currentUSer",getUserData());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("currentUSer",getUserData());
        return "register";
    }
    @PostMapping("/toregister")
    public String toregister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String repassword,
                             @RequestParam(name = "full_name") String fullName){
         if(password.equals(repassword)){

             Users newUSer=new Users();
             newUSer.setFullName(fullName);
             newUSer.setPassword(password);
             newUSer.setEmail(email);
if(registerService.addUser(newUSer)!=null) {
    return "redirect:/login";
}
         }
         return "redirect:/register?error";
    }


    @GetMapping("/403")
    public String accessDenied(Model model){
        model.addAttribute("currentUSer",getUserData());
        return "403";
    }
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUSer",getUserData());
        return "profile";
    }

    private Users getUserData(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser=(User) authentication.getPrincipal();
            Users myUser= userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
