package com.example.lab7.Controller;


import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if ( !studentService.addStudent(student))
            return ResponseEntity.status(400).body(new ApiResponse( "ID already exists!"));
        return ResponseEntity.status(200).body(new ApiResponse( "Added Successfully"));
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity<?> updateStudent(@PathVariable String ID, @RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if ( !studentService.updateStudent(ID, student))
            return ResponseEntity.status(400).body(new ApiResponse( "Did not find a student with that ID"));
        return ResponseEntity.status(200).body(new ApiResponse("Updated Successfully"));
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteStudent(@PathVariable String ID){
        if ( !studentService.deleteStudent(ID))
            return ResponseEntity.status(400).body(new ApiResponse("Did not find a student with ID. deletion Failed"));
        return ResponseEntity.status(200).body(new ApiResponse("Deleted Successfully"));
    }

    @GetMapping("/getByID/{ID}")
    public ResponseEntity<?> getByID(@PathVariable String ID){
        if (studentService.getByID(ID)== null)
            return ResponseEntity.status(400).body(new ApiResponse("Student with that ID was not found"));
        return ResponseEntity.status(200).body(studentService.getByID(ID));
    }

    @GetMapping("/getByGPA/{gpa}")
    public ResponseEntity<?> getByGPA(@PathVariable double gpa){
        if (studentService.getByGPA(gpa).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Students with that GPA or higher were NOT FOUND"));
        return ResponseEntity.status(200).body(studentService.getByGPA(gpa));
    }

}
