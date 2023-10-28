/*
 * File: src\main\java\com\taylor\aop\orm\repositories\StudentRepositoryCustom.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 9:07:48 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 10:20:16 am
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 27th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.orm.repositories;

import java.util.List;
import java.util.Set;

import com.taylor.aop.orm.entities.Student;

public interface StudentRepositoryCustom {

    List<Student> findLikeNames(Set<String> names);
}