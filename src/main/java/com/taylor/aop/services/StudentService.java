/*
 * File: src\main\java\com\taylor\aop\services\StudentService.java
 * Project: jpa
 * Created Date: Friday, October 27th 2023, 9:09:37 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 4:26:43 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 27th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taylor.aop.annotations.OperationTrace;
import com.taylor.aop.exceptions.ResourceAlreadyExistsException;
import com.taylor.aop.exceptions.ResourceNotFoundException;
import com.taylor.aop.orm.entities.Student;
import com.taylor.aop.orm.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Page<Student> getAllStudents(Optional<Integer> page, Optional<Integer> size) {
        // Notice: page is start from 0
        Pageable pageable = PageRequest.of(0, 10);
        if (page.isPresent() && size.isPresent()) {
            pageable = PageRequest.of(page.get(), size.get());
        }

        return studentRepository.findAll(pageable);
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exists with id:" + id));
    }

    public Student getStudentByName(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exists with name:" + name));
    }

    @OperationTrace(action = "Create", traceOn = "Student")
    public Student create(Student student) {
        Optional<Student> existedStudent = studentRepository.findByName(student.getName());
        if (existedStudent.isPresent()) {
            throw new ResourceAlreadyExistsException("Student with name " + student.getName() +
                    " already exists!");
        }

        return studentRepository.save(student);
    }

    @OperationTrace(action = "Update", traceOn = "Student")
    public Student update(Long id, Student student) {
        Student existedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exists with id:" + id));

        existedStudent.setName(student.getName());
        existedStudent.setAge(student.getAge());
        existedStudent.setDescription(student.getDescription());
        existedStudent.setScore(student.getScore());
        return studentRepository.save(existedStudent);
    }

    @OperationTrace(action = "Delete", traceOn = "Student")
    public void delete(Long id) {
        Student existedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exists with id:" + id));
        studentRepository.delete(existedStudent);
    }
}