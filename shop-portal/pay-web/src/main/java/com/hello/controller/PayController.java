package com.hello.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {

    @RequestMapping("indes")
    public String indes() {
        return "主页";
    }
}
