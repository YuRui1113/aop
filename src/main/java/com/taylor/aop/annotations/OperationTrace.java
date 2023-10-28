/*
 * File: src\main\java\com\taylor\aop\annotations\OperationTrace.java
 * Project: jpa
 * Created Date: Saturday, October 28th 2023, 4:04:12 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 8:18:39 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Saturday, October 28th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationTrace {

    String action();

    String traceOn();
}
