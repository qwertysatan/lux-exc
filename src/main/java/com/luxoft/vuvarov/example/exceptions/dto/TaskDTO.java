package com.luxoft.vuvarov.example.exceptions.dto;

import com.luxoft.vuvarov.example.exceptions.emumerations.TaskStatus;
import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskDTO implements Serializable {
    
    private Long taskId;
    
    private TaskStatus status;
    
    private TaskType type;
    
    private LocalDateTime createTime;
    
    private LocalDateTime statusTime;
    
    
}
