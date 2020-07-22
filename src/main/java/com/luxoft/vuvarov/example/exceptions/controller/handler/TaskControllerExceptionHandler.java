package com.luxoft.vuvarov.example.exceptions.controller.handler;

import com.luxoft.vuvarov.example.exceptions.exception.ControllerParamsException;
import com.luxoft.vuvarov.example.exceptions.exception.TaskNotFoundException;
import com.luxoft.vuvarov.example.exceptions.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class TaskControllerExceptionHandler {
    
    private final NotificationService notificationService;
    
    /**
     * Уведомлеям пользователя о том, что задача не найдена
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public ModelAndView handleTaskNotFoundException(HttpServletRequest request, TaskNotFoundException e){
        log.error("Task not found", e);
        return getMav(request.getRequestURL().toString(), e.getMessage());
    }
    
    
    /**
     * Уведомляем пользователя о некорректном запросе
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ControllerParamsException.class)
    public ModelAndView handleIncorrectParam(HttpServletRequest request, ControllerParamsException e){
        log.error("Incorrect param", e);
        return getMav(request.getRequestURL().toString(), e.getMessage());
    }
    
    /**
     * Скрываем от пользователя стектрейс в случае непредвиденной ошибки
     * */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleUnexpectedException(HttpServletRequest request, Exception e){
        log.error("Unexpected error", e);
        notificationService.notify(e); //отправляем уведомление об ошибке
        return getMav(request.getRequestURL().toString(), "Unexpected error has occurred, please contact to your system administrator");
    }
    
    private ModelAndView getMav(String url, String message){
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", url);
        mav.addObject("message", message);
        mav.setViewName("error");
        return mav;
    }
}
