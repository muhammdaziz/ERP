package com.example.erp.exceptions;

import com.example.erp.payload.ApiResult;
import com.example.erp.payload.ErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = RestException.class)
    public ApiResult<ErrorData> exceptionHandle(RestException ex) {
        log.error("Exception: ",ex);
        return ApiResult.errorResponse(ex.getMessage(),
                        ex.getStatus().value());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<ErrorData> exceptionHandle(
            MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ErrorData> errorDataList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors)
            errorDataList.add(
                    new ErrorData(fieldError.getDefaultMessage(),
                            HttpStatus.BAD_REQUEST.value(),
                            fieldError.getField()));

        return ApiResult.errorResponse(errorDataList);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ApiResult<ErrorData> exceptionHandle(EmptyResultDataAccessException ex) {
         return ApiResult.errorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ApiResult<ErrorData> exceptionHandle(AccessDeniedException ex) {
         return ApiResult.errorResponse("Huquqingiz yo'q okasi", HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(value = InsufficientAuthenticationException.class)
    public ApiResult<ErrorData> exceptionHandle(InsufficientAuthenticationException ex) {
         return ApiResult.errorResponse("Full authentication is required to access this resource", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResult<ErrorData> exceptionHandle(Exception ex) {
        log.error("Exception: ",ex);
         return ApiResult.errorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }
    @ExceptionHandler(value = AccountStatusException.class)
    public ApiResult<ErrorData> exceptionHandle(AccountStatusException ex) {
        log.error("Exception: ",ex);
         return ApiResult.errorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }


}
