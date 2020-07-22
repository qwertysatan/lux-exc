package com.luxoft.vuvarov.example.exceptions.service.impl;

import com.luxoft.vuvarov.example.exceptions.dto.TaskDTO;
import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;
import com.luxoft.vuvarov.example.exceptions.entity.TaskEntity;
import com.luxoft.vuvarov.example.exceptions.exception.TaskNotFoundException;
import com.luxoft.vuvarov.example.exceptions.repository.TaskRepository;
import com.luxoft.vuvarov.example.exceptions.service.TaskService;
import com.luxoft.vuvarov.example.exceptions.utils.TaskDtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.luxoft.vuvarov.example.exceptions.utils.TaskDtoUtils.convertTaskEntityToDto;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    
    @Override
    public List<TaskDTO> findAll(){
        return taskRepository.findAll().stream().map(TaskDtoUtils::convertTaskEntityToDto).collect(Collectors.toList());
    }
    
    @Override
    public TaskDTO createNewTask(TaskType type){
        return convertTaskEntityToDto(taskRepository.save(TaskEntity.builder().type(type).build()));
    }
    
    @Override
    public TaskDTO findTaskById(Long taskId) {
        try{
            return convertTaskEntityToDto(taskRepository.getOne(taskId));
        }catch (EntityNotFoundException e){
            throw new TaskNotFoundException(taskId, e);
        }
    }
}
