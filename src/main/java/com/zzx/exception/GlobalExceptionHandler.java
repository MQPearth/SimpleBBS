package com.zzx.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHander(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

