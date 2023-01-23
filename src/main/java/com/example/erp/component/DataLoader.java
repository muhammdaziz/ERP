package com.example.erp.component;

import com.example.erp.entity.Role;
import com.example.erp.entity.User;
import com.example.erp.entity.enums.PageEnum;
import com.example.erp.entity.enums.PermissionEnum;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String email;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeType;

    @Value("${spring.datasource.username}")
    private String dbName;

    @Override
    public void run(String... args) {
        if (Objects.equals("create", modeType)) {
            addAdmin();
        }
    }


    private void addAdmin() {
        userRepository.save(
                User.builder()
                        .firstname("Admin")
                        .lastname("")
                        .email(email)
                        .password(passwordEncoder.encode(adminPassword))
                        .role(addAdminRole())
                        .enabled(true)
                        .build());
    }

    private Role addAdminRole() {
        return roleRepository.save(
                new Role("ADMIN",
                        "System owner",
                        PageEnum.ROLE,
                        Set.of(PermissionEnum.values()),
                        Set.of(PageEnum.values())
                )
        );
    }
}
