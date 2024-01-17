package com.Arzu.library_project.Service;

import com.Arzu.library_project.DTO.StudentRequestDto;
import com.Arzu.library_project.DTO.StudentResponseDto;
import com.Arzu.library_project.DTO.StudentUpdateEmailRequestDto;
import com.Arzu.library_project.Entity.LibraryCard;
import com.Arzu.library_project.Entity.Student;
import com.Arzu.library_project.Enum.CardStatus;
import com.Arzu.library_project.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto){

        Student student= new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);

    }
    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        Student student= studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());
        //update it will override existing student obj
        Student updatedStudent = studentRepository.save(student);
        // conversion of updated student obj to dto
        StudentResponseDto studentResponseDto= new StudentResponseDto();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());

        return studentResponseDto;
    }
}
