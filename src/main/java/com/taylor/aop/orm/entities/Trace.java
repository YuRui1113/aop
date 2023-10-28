/*
 * File: src\main\java\com\taylor\aop\orm\entities\Trace.java
 * Project: jpa
 * Created Date: Saturday, October 28th 2023, 3:46:52 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 3:48:33 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Saturday, October 28th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.orm.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "traces", schema = "public")
@ToString
public class Trace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(name = "traceOn")
    private String traceOn;

    @Getter
    @Setter
    @Column(name = "action")
    private String action;

    @Getter
    @Setter
    @Column(name = "request", length = 500)
    private String request;

    @Getter
    @Setter
    @Column(name = "traceTS")
    @CreationTimestamp // Providing default value
    private Timestamp traceTS;

    // The default constructor exists only for the sake of JPA.
    protected Trace() {
    }

    public Trace(String traceOn, String action, String request) {
        this.traceOn = traceOn;
        this.action = action;
        this.request = request;
    }
}