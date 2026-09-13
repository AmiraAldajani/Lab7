package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public boolean addCourse(Course course){
        for (Course s: courses){
            if (course.getTitle().equals(s.getTitle())&& s.getUnderMajor().equals(course.getUnderMajor())){
                return false;
            }
        }
        courses.add(course);
        return true;
    }

    public boolean updateCourse(String title, Course course){
        for ( int i =0; i<courses.size(); i++)
            if ( title.equals(courses.get(i).getTitle())){
                courses.set(i,course);
                return true;
            }
        return false;
    }

    public boolean deleteCourse(String title){
        for ( Course course: courses)
            if ( title.equals(course.getTitle())) {
                courses.remove(course);
                return true;
            }
        return false;
    }

    public Course getByTitle(String title){
        for ( Course course: courses){
            if (course.getTitle().equals(title))
                return course;
        }
        return null;
    }

    public ArrayList<Course> getByHours(int hour){
        ArrayList<Course> coursesByHours = new ArrayList<>();
        for ( Course course: courses){
            if (course.getHours()==hour)
                coursesByHours.add(course);
        }
        return coursesByHours;
    }
}
