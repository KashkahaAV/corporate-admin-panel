package com.corporate.adminpanel.controller;

import com.corporate.adminpanel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;


    @GetMapping("/users")
    public String getUsersPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }


    @PostMapping("/users/{id}/status")
    public String toggleUserStatus(@PathVariable Long id) {
        userService.toggleUserStatus(id);
        return "redirect:/admin/users";
    }
}