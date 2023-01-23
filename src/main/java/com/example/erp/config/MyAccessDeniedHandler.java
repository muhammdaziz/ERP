package com.example.erp.config;

import com.example.erp.exceptions.RestExceptionHandler;
import com.example.erp.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private final RestExceptionHandler restExceptionHandler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("___ ".repeat(8));
        ApiResult<?> responseEntity = restExceptionHandler.exceptionHandle(accessDeniedException);
        System.out.println(responseEntity);
    }
}
