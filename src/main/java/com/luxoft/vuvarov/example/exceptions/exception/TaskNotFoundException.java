package com.luxoft.vuvarov.example.exceptions.exception;

import lombok.NonNull;

public class TaskNotFoundException extends SystemException {
    
    private Long taskId;
    
    /**
     * Создаем исключение с идентификатором задачи, которую мы не нашли в БД
     * */
    public TaskNotFoundException(@NonNull Long taskId, Exception cause) {
        super(cause);
        this.taskId = taskId;
    }
    
    @Override
    public String getMessage() {
        return String.format("Task with id %d not found", taskId);
    }
}
