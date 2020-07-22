package com.luxoft.vuvarov.example.exceptions.service.impl;

import com.luxoft.vuvarov.example.exceptions.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    
    
    /**
     * Пробуем уведомить администратора об ошибке, например с помощью электронной почты
     * */
    @Override
    public void notify(Exception e){
        try{
            //Логика отправки уведомления (либо делегат)
        }catch (Exception notificationException){
            log.error("Notification error", notificationException);
        }
    }
    
}
