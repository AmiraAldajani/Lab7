package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if ( !courseService.addCourse(course))
            return ResponseEntity.status(400).body(new ApiResponse( "Title under the same major already exists!"));
        return ResponseEntity.status(200).body(new ApiResponse( "Added Successfully"));
    }

    @PutMapping("/update/{title}")
    public ResponseEntity<?> updateCourse(@PathVariable String title, @RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if ( !course.getTitle().equals(title))
            return ResponseEntity.status(400).body(new ApiResponse("Can't change title (القيمة ثابتة). any other variable is fine."));
        if ( !courseService.updateCourse(title, course))
            return ResponseEntity.status(400).body(new ApiResponse( "Did not find a course with that title"));
        return ResponseEntity.status(200).body(new ApiResponse("Updated Successfully"));
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<?> deleteCourse(@PathVariable String title){
        if ( !courseService.deleteCourse(title))
            return ResponseEntity.status(400).body(new ApiResponse("Did not find a student with ID. deletion Failed"));
        return ResponseEntity.status(200).body(new ApiResponse("Deleted Successfully"));
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title) {
        if (courseService.getByTitle(title) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Course with that title was not found"));
        return ResponseEntity.status(200).body(courseService.getByTitle(title));
    }

    @GetMapping("/getByHours/{hour}")
    public ResponseEntity<?> getByGPA(@PathVariable int hour){
        if (courseService.getByHours(hour).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Courses were NOT FOUND"));
        return ResponseEntity.status(200).body(courseService.getByHours(hour));
    }
}
