package com.hello.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/home")
@RestController
public class HomeController {


    @GetMapping("/toHome")
    public ModelAndView toHome() {
        return new ModelAndView("home");
    }

    @GetMapping("/toRegister")
    public ModelAndView register() {
        return new ModelAndView("register");
    }


}
