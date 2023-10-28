/*
 * File: src\main\java\com\taylor\aop\orm\repositories\TraceRepository.java
 * Project: jpa
 * Created Date: Saturday, October 28th 2023, 3:51:05 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 8:18:02 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Saturday, October 28th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.orm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taylor.aop.orm.entities.Trace;

public interface TraceRepository extends JpaRepository<Trace, Long> {
}