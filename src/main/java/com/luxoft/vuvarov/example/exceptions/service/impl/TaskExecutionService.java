package com.luxoft.vuvarov.example.exceptions.service.impl;

import com.luxoft.vuvarov.example.exceptions.emumerations.TaskStatus;
import com.luxoft.vuvarov.example.exceptions.entity.TaskEntity;
import com.luxoft.vuvarov.example.exceptions.repository.TaskRepository;
import com.luxoft.vuvarov.example.exceptions.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskExecutionService {
    
    private final int THREAD_CAPACITY = 10;
    
    private final TaskRepository taskRepository;
    
    private final NotificationService notificationService;
    
    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_CAPACITY);
    
    private ArrayBlockingQueue<TaskEntity> executingTasks = new ArrayBlockingQueue<>(THREAD_CAPACITY);
    
    /**
     * В цикле запускаем поочередное выполнение задач. Если задачи в БД кончатся, ждем 10 секунд, и ищем снова.
     * */
    @Scheduled(fixedRate = 10000L)
    void startExecuting() {
        for (TaskEntity task = getTaskForExecution(); task != null; task = getTaskForExecution()) {
            executeTask(task);
        }
    }
    
    private TaskEntity getTaskForExecution() {
        return taskRepository.findFirstByStatusOrderByStatusTime(TaskStatus.NEW);
    }
    
    private void executeTask(TaskEntity task) {
        executingTasks.add(task); //берем блокировку
        updateStatus(task, TaskStatus.IN_PROGRESS);
        switch (task.getType()) {
            case SOME_TASK_TYPE: executorService.submit(new SomeTaskExecutor(task));
        }
    }
    
    private void updateStatus(TaskEntity task, TaskStatus status){
        task.setStatus(status);
        taskRepository.save(task);
    }
    
    private class SomeTaskExecutor implements Runnable {
        
        private TaskEntity taskEntity;
        
        SomeTaskExecutor(TaskEntity taskEntity){
            this.taskEntity = taskEntity;
        }
        
        @Override
        public void run() {
            try {
                processTask();
                updateStatus(taskEntity, TaskStatus.COMPLETED);
            } catch (Exception e) {
                //Обновляем статус задачи на ошибку
                log.error("Task execution error", e);
                updateStatus(taskEntity, TaskStatus.FAILED);
                notificationService.notify(e);
            } finally {
                //Отпускаем блокировку (если заняты другие ресурсы, их отпускаем тоже)
                executingTasks.remove(taskEntity);
            }
        }
        
        private void processTask(){
            //выполняем какую то логику обработки
        }
        
    }
    
    
    
}
