/*
 * File: src\main\java\com\taylor\aop\exceptions\GlobalExceptionHandler.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 9:52:59 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 10:22:54 am
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 27th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
    }
}