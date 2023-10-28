/*
 * File: src\main\java\com\taylor\aop\orm\entities\Student.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 8:31:45 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 10:19:43 am
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 27th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.orm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students", schema = "public")
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "sex")
    private Boolean sex;

    @Getter
    @Setter
    @Column(name = "age")
    private Integer age;

    @Getter
    @Setter
    @Column(name = "score")
    private Integer score;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    protected Student() {
    }

    public Student(String name, Boolean sex, Integer age, String description) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.description = description;
    }
}