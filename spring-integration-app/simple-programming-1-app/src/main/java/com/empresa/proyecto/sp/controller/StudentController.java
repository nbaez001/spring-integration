package com.empresa.proyecto.sp.controller;

import com.empresa.proyecto.sp.intfc.MessageGateway;
import com.empresa.proyecto.sp.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final MessageGateway messageGateway;

    @PostMapping
    public void processStudentDetails(@RequestBody Student student) {
        messageGateway.sendMessage(student);
    }
}
