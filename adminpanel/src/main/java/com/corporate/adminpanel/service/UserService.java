package com.corporate.adminpanel.service;

import com.corporate.adminpanel.model.User;
import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    void toggleUserStatus(Long userId);
    void registerUser(User user);
}