package com.Arzu.library_project.Controller;

import com.Arzu.library_project.DTO.StudentRequestDto;
import com.Arzu.library_project.DTO.StudentResponseDto;
import com.Arzu.library_project.DTO.StudentUpdateEmailRequestDto;
import com.Arzu.library_project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto)
    {
        studentService.addStudent(studentRequestDto);
        return "student has been added";
    }
    @PutMapping("/update_email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }
}
