package com.corporate.adminpanel.service;

import com.corporate.adminpanel.model.Role;
import com.corporate.adminpanel.model.User;
import com.corporate.adminpanel.repository.RoleRepository;
import com.corporate.adminpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void registerUser(User user) {
        // 1. Надежно шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 2. Ищем готовую роль ROLE_USER в базе (она создается при старте)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Ошибка: Роль ROLE_USER не найдена в базе данных!"));

        // 3. Привязываем роль и сохраняем пользователя
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }

    @Override
    public void toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // Инвертируем статус: true меняется на false, а false на true
        user.setActive(!user.isActive());

        userRepository.save(user);
    }
}