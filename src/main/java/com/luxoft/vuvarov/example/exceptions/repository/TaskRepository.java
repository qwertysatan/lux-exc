package com.luxoft.vuvarov.example.exceptions.repository;

import com.luxoft.vuvarov.example.exceptions.emumerations.TaskStatus;
import com.luxoft.vuvarov.example.exceptions.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    
    TaskEntity findFirstByStatusOrderByStatusTime(TaskStatus status);
    
}
