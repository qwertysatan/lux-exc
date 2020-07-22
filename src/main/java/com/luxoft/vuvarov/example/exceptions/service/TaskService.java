package com.luxoft.vuvarov.example.exceptions.service;

import com.luxoft.vuvarov.example.exceptions.dto.TaskDTO;
import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;

import java.util.List;

public interface TaskService {
    
    List<TaskDTO> findAll();
    
    TaskDTO createNewTask(TaskType type);
    
    TaskDTO findTaskById(Long taskId);
    
}
