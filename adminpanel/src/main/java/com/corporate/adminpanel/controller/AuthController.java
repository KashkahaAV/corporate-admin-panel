package com.corporate.adminpanel.controller;

import com.corporate.adminpanel.model.User;
import com.corporate.adminpanel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Вот этого метода не хватало для отображения страницы входа
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Возвращает файл login.html из папки templates
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login?registered";
    }
}