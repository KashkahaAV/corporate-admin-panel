package com.corporate.adminpanel.config;

import com.corporate.adminpanel.model.Role;
import com.corporate.adminpanel.model.User;
import com.corporate.adminpanel.repository.RoleRepository;
import com.corporate.adminpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    return roleRepository.save(role);
                });


        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@corporate.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setActive(true);
            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);
        }
    }
}