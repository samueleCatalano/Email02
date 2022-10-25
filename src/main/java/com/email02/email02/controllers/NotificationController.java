package com.email02.email02.controllers;


import com.email02.email02.entities.Student;

import com.email02.email02.services.StudentService;
import com.email02.email02.entities.Student;
import com.email02.email02.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    com.email02.email02.services.EmailService emailService;

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity sendMail(@RequestBody com.email02.email02.dto.NotificationDTO payload){
        try {
            Student studentToSendNotification = studentService.getStudentById(payload.getContactId());
            if (studentToSendNotification == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find the answer");
            } else {
                emailService.sendTo(studentToSendNotification.getEmail(), payload.getTitle(), payload.getText());
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }catch (Exception e){
            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}