package com.simpleform.simpleform.controller;

import com.simpleform.simpleform.model.UsersModel;
import com.simpleform.simpleform.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //Return a html register user page
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        //Use model to put attribute in model
        //When the page render, it will put the login request with EMPTY usermodel on that page, and then
        // on Thymeleaf, we use this object to register user
        model.addAttribute("registerRequest",new UsersModel());
        return "register_page";
    }


    //Return html login page
    @GetMapping("/login")
    public String getLoginPage(Model model){
        //When the page render, it will put the login request with EMPTY usermodel on that page, and then
        // on Thymeleaf, we use this object to register user
        model.addAttribute("loginRequest",new UsersModel());
        return "login_page";
    }

    //Controller to handle incoming register and login request
    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser =  usersService.registerUser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        //If register user is null, we return error page, otherwise, redirect them to login
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated =  usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin",authenticated.getLogin());
            return "personal_page";
        }
        return "error_page";
    }




}
