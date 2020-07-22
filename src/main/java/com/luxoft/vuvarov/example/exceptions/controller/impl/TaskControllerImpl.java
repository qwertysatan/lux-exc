package com.luxoft.vuvarov.example.exceptions.controller.impl;

import com.luxoft.vuvarov.example.exceptions.controller.TaskController;
import com.luxoft.vuvarov.example.exceptions.dto.TaskDTO;
import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;
import com.luxoft.vuvarov.example.exceptions.service.TaskService;
import com.luxoft.vuvarov.example.exceptions.utils.TaskControllerValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {
    
    private final TaskService taskService;
    
    @Override
    public List<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }
    
    @Override
    public TaskDTO createNewTask(String taskType) {
        TaskControllerValidationUtils.validateTaskType(taskType);
        return taskService.createNewTask(TaskType.valueOf(taskType));
    }
    
    @Override
    public TaskDTO getTaskById(Long taskId) {
        return taskService.findTaskById(taskId);
    }
}
