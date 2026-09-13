package com.example.lab7.Service;

import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }

    public boolean addStudent(Student student){
        for (Student s: students){
            if (student.getID().equals(s.getID())){
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(String ID, Student student){
        for ( int i =0; i<students.size(); i++)
            if ( ID.equals(students.get(i).getID())){
                students.set(i,student);
                return true;
            }
        return false;
    }

    public boolean deleteStudent(String ID){
        for ( Student s: students)
            if ( ID.equals(s.getID())) {
                students.remove(s);
                return true;
            }
        return false;
    }

    public Student getByID(String ID){
        for ( Student student: students){
            if (student.getID().equals(ID))
                return student;
        }
        return null;
    }

    public ArrayList<Student> getByGPA(double gpa){
        ArrayList<Student> studentByGPA = new ArrayList<>();
        for ( Student student: students){
            if (student.getGpa()>= gpa)
                studentByGPA.add(student);
        }
        return studentByGPA;
    }
}
