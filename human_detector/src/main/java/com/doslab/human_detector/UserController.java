package com.doslab.human_detector;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/profile")
    public String show_admin_profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = DataBase.get_user(username);

            model.addAttribute("user", user); // Add the user object to the model
        } else {
            return "redirect:/login"; // Перенаправление на страницу входа
        }

        return "admin_profile"; // Return the view name
    }

}
