package com.example.erp.entity.enums;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import static com.example.erp.entity.enums.PageEnum.*;

@AllArgsConstructor
public enum PermissionEnum implements GrantedAuthority {

    ADD_ROLE(ROLE),
    GET_ROLES(ROLE),
    GET_ROLE(ROLE),
    EDIT_ROLE(ROLE),
    DELETE_ROLE(ROLE),
    ADD_USER(USER),
    GET_USERS(USER),
    GET_USER(USER),
    GET_USERS_FOR_STATION(USER),
    EDIT_USER(USER),
    DELETE_USER(USER);

    private final PageEnum page;

    @Override
    public String getAuthority() {
        return name();
    }
}
