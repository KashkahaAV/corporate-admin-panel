package com.corporate.adminpanel.controller;

import com.corporate.adminpanel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // Дополнительная защита всего контроллера на уровне ролей
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService; // Зависимость внедряется через конструктор (SOLID)

    // Получить список пользователей для отображения в админке
    @GetMapping("/users")
    public String getUsersPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users"; // Имя HTML-файла (Thymeleaf шаблона)
    }

    // Изменить статус пользователя (активировать / заблокировать)
    @PostMapping("/users/{id}/status")
    public String toggleUserStatus(@PathVariable Long id) {
        userService.toggleUserStatus(id);
        return "redirect:/admin/users"; // Возвращаем обратно на страницу админки
    }
}