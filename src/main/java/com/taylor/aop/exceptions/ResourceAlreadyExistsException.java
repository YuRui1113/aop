/*
 * File: src\main\java\com\taylor\aop\exceptions\ResourceAlreadyExistsException.ajva
 * Project: c:\Work\Docs\aop\aop\src\main\java\com\taylor\aop\exceptions
 * Created Date: Friday, October 27th 2023, 9:51:30 pm
 * Author: Rui Yu
 * -----
 * Last Modified: Saturday, 28th October 2023 10:23:01 am
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * 
 * All shall be well and all shall be well and all manner of things shall be well.
 * Nope...we're doomed!
 * -----
 * HISTORY:
 * Date      	By	Comments
 * ----------	---	----------------------------------------------------------
 */

package com.taylor.aop.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}