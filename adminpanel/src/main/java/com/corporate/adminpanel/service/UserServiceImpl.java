package com.corporate.adminpanel.service;

import com.corporate.adminpanel.model.User;
import com.corporate.adminpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Автоматически создает конструктор для final полей (DIP)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Внедрение репозитория

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional // Гарантирует, что транзакция откатится в случае ошибки
    public void toggleUserStatus(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + userId + " не найден"));

        // Меняем статус на противоположный (активен <-> заблокирован)
        user.setActive(!user.isActive());
        userRepository.save(user);
    }
}