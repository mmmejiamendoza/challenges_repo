package com.spring.demo.rest;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.spring.demo.domain.Student;
import com.spring.demo.persistence.StudentRepository;

@Controller 
public class DemoGraphQLController {
    private final StudentRepository studentRepository;

    // inject it via constructor
    public DemoGraphQLController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @QueryMapping
    public String greeting(@Argument String name) {
        String targetName = (name != null && !name.isBlank()) ? name : "Friend";
        return "hello, " + targetName + "! welcome to spring graphQL";
    }

    @MutationMapping 
    public StudentResponse createStudent(@Argument String firstName, @Argument String lastName, @Argument String email) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        Student savedStudent = studentRepository.save(student);
        
        return new StudentResponse(savedStudent.getId().toString(), savedStudent.getFirstName(), savedStudent.getLastName(), savedStudent.getEmail());
    }

    @MutationMapping
    public Boolean deleteStudent(@Argument Integer id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    

    public record StudentResponse(String id, String firstName, String lastName, String email){}
}