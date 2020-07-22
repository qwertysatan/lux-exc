package com.luxoft.vuvarov.example.exceptions.utils;

import com.luxoft.vuvarov.example.exceptions.dto.TaskDTO;
import com.luxoft.vuvarov.example.exceptions.entity.TaskEntity;

public class TaskDtoUtils {
    
    /**
     * Преобразование Entity в DTO
     * */
    public static TaskDTO convertTaskEntityToDto(TaskEntity taskEntity){
        return TaskDTO
                .builder()
                .taskId(taskEntity.getId())
                .status(taskEntity.getStatus())
                .type(taskEntity.getType())
                .createTime(taskEntity.getCreateTime())
                .statusTime(taskEntity.getStatusTime())
                .build();
    }
    
}
