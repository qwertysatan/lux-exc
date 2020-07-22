package com.luxoft.vuvarov.example.exceptions.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SystemException extends RuntimeException {
    
    public SystemException(Exception cause){
        super(cause);
    }
    
}
