// controller/HomeController.java
package com.thuexe.thuexe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String showHomePage() {
        return "home";        // Trả về file home.html
    }
}