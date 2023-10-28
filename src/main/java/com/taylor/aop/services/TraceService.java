/*
 * File: src\main\java\com\taylor\aop\services\TraceService.java
 * Project: jpa
 * Created Date: Saturday, October 28th 2023, 3:52:30 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 8:17:44 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Saturday, October 28th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.services;

import org.springframework.stereotype.Service;

import com.taylor.aop.orm.entities.Trace;
import com.taylor.aop.orm.repositories.TraceRepository;

@Service
public class TraceService {

    private final TraceRepository repository;

    public TraceService(TraceRepository repository) {
        this.repository = repository;
    }

    public Trace createTrace(Trace trace) {
        return repository.save(trace);
    }
}