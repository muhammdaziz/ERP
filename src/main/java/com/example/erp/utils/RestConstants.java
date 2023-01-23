package com.example.erp.utils;

import com.example.erp.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {
    ObjectMapper objectMapper = new ObjectMapper();

    String AUTHENTICATION_HEADER = "Authorization";

    String AUTHORIZATION_HEADER = "Authorization";

    String REQUEST_ATTRIBUTE_CURRENT_USER = "User";
    String REQUEST_ATTRIBUTE_CURRENT_USER_ID = "UserId";
    String REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS = "Permissions";

    String BASE_PATH = "/api/v1/";

    String[] OPEN_PAGES = {
            "/*",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
            RestConstants.BASE_PATH + "/**"
    };
    String DEFAULT_PAGE = "0";
    String NO = "NO";
    String YES = "YES";
}
