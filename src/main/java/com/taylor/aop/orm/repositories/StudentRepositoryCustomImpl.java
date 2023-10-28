/*
 * File: src\main\java\com\taylor\aop\orm\repositories\StudentRepositoryCustomImpl.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 9:08:46 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 8:18:09 pm
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.taylor.aop.orm.entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findLikeNames(Set<String> names) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);

        Root<Student> student = query.from(Student.class);
        Path<String> namePath = student.get("name");

        List<Predicate> predicates = new ArrayList<>();
        for (String name : names) {
            predicates.add(builder.like(namePath, "%" + name + "%"));
        }

        query.select(student)
                .where(builder.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}