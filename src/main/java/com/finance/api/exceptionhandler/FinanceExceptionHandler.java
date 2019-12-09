package com.finance.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@ControllerAdvice
public class FinanceExceptionHandler  extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("invalid.message", null, Objects.requireNonNull(LocaleContextHolder.getLocaleContext()).getLocale());
        String devMessage = ex.getCause().toString();
        List<Error> errors = Collections.singletonList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, errors,headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = createListError(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EntityNotFoundException.class, EmptyResultDataAccessException.class })
    protected ResponseEntity<Object> handleExceptionEntityNotFound(RuntimeException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("notfound.message", null, Objects.requireNonNull(LocaleContextHolder.getLocaleContext()).getLocale());
        String devMessage = Arrays.toString(ex.getStackTrace());
        List<Error> errors = Collections.singletonList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Error> createListError(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, Objects.requireNonNull(LocaleContextHolder.getLocaleContext()).getLocale());
            String devMessage = fieldError.toString();
            errors.add(new Error(userMessage, devMessage));
        }
        return errors;
    }

    public static class Error {

        private String userMessage;
        private String devMessage;

        public Error(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDevMessage() {
            return devMessage;
        }
    }
}
