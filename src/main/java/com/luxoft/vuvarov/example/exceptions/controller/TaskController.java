package com.luxoft.vuvarov.example.exceptions.controller;

import com.luxoft.vuvarov.example.exceptions.dto.TaskDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/task")
public interface TaskController {
    
    @GetMapping("/list")
    @ApiOperation("Получить список задач")
    List<TaskDTO> getAllTasks();
    
    @PutMapping("/new")
    @ApiOperation("Создать новую задачу")
    TaskDTO createNewTask(@PathVariable("taskType") String taskType);
    
    @GetMapping("/{taskId}")
    @ApiOperation("Найти задачу по id")
    TaskDTO getTaskById(@PathVariable Long taskId);
    
}
