package com.luxoft.vuvarov.example.exceptions.utils;

import com.luxoft.vuvarov.example.exceptions.emumerations.TaskType;
import com.luxoft.vuvarov.example.exceptions.exception.ControllerParamsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskControllerValidationUtils {
    
    private static final String TASK_TYPE_PARAM_NAME = "taskType";

    /**
     * Проверяем параметр на корректность, и в случая непрохождения проверки, кидаем исключение
     *
     * Конкретный пример несколько далек от реальности (преобразование строки в enum), и присутствует только для иллюстрирования использования исключений при валидации
     * */
    public static void validateTaskType(String taskTypeVal){
        TaskType taskType;
        try{
            taskType = TaskType.valueOf(taskTypeVal);
        }catch (Exception e){
            log.error("Task type validation error", e);
            throw new ControllerParamsException(TASK_TYPE_PARAM_NAME, taskTypeVal);
        }
        if(taskType == null){
            throw new ControllerParamsException(TASK_TYPE_PARAM_NAME, null);
        }
    }


}
