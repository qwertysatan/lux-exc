package com.luxoft.vuvarov.example.exceptions.exception;

import lombok.NonNull;

public class ControllerParamsException extends SystemException {

    private String paramName;
    
    private Object paramValue;
    
    /**
     * Создаем исключениие с названием и значением некорректного параметра
     * */
    public ControllerParamsException(@NonNull String paramName, Object paramValue){
            this.paramName = paramName;
            this.paramValue = paramValue;
    }
    
    @Override
    public String getMessage() {
        return paramValue == null ? getNullValueMessage() : getDefaultMessage();
    }
    
    private String getNullValueMessage(){
        return String.format("Param %s should be not nul", paramName);
    }
    
    private String getDefaultMessage(){
        return String.format("Incorrect value '%s' of param %s", paramValue.toString(), paramName);
    }
}
