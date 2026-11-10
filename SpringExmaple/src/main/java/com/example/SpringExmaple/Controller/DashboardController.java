package com.example.SpringExmaple.Controller;


import com.example.SpringExmaple.Configuration.CustomUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {

    	CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        model.addAttribute("profile", user);

        return "dashboard";
    }
}