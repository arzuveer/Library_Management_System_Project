package com.Arzu.library_project.DTO;

import com.Arzu.library_project.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentRequestDto {
    private String name;
    private int age;
    private Department department;
    private String email;
}
