/*
 * File: src\main\java\com\taylor\aop\controllers\StudentController.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 9:26:05 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 3:56:10 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 27th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taylor.aop.orm.entities.Student;
import com.taylor.aop.services.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Get all students
    @GetMapping()
    public Page<Student> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return service.getAllStudents(page, size);
    }

    // Create a new student
    @PostMapping()
    public Student createStudent(@RequestBody Student student) {
        return service.create(student);
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    // Get student by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getStudentByName(name));
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(service.update(id, studentDetails));
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        service.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}