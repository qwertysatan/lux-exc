package com.luxoft.vuvarov.example.exceptions.entity;

import com.luxoft.vuvarov.example.exceptions.emumerations.TaskStatus;
import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskStatus status = TaskStatus.NEW;
    
    @Enumerated(EnumType.STRING)
    @NonNull
    private TaskType type;
    
    @Column(name = "create_time")
    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();
    
    @Column(name = "status_time")
    @Builder.Default
    private LocalDateTime statusTime = LocalDateTime.now();
    
}
