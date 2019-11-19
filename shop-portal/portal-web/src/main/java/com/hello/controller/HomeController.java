package com.hello.controller;


import com.hello.entity.PageData;
import com.hello.feign.IUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/home")
@RestController
public class HomeController {

    @Autowired
    IUserFeign userFeign;

    @GetMapping("/toHome")
    public ModelAndView toHome() {
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView register() {
        return new ModelAndView("login");
    }


    @PostMapping("/authentication")
    public ModelAndView authentication(@RequestParam Map param) {
        Map result = new HashMap();
        PageData pd = new PageData();
        pd.putAll(param);
        if (Objects.isNull(pd.get("username"))) {
            result.put("code", 201);
            return new ModelAndView("redirect:/home/login");
        }
        pd.put("loginType", "PC");
        PageData login = this.userFeign.login(pd);
        return new ModelAndView("redirect:/home/toHome");
    }


}
