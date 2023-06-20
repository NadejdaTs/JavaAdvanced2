package university;

import java.util.ArrayList;
import java.util.List;

public class University {
    public int capacity;
    public List<Student> students;

    public University(int capacity){
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }
    public int getStudentCount(){
        return this.students.size();
    }

    public String registerStudent(Student student){
        if(getStudentCount() < this.capacity){
            if(!this.students.contains(student)){
                this.students.add(student);
                return String.format("Added student %s %s", student.getFirstName(), student.getLastName());
            }else{
                return "Student is already in the university";
            }
        }else{
            return "No seats in the university";
        }
    }

    public String dismissStudent(Student student){
        if(this.students.contains(student)){
            this.students.remove(student);
            return String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
        }else{
            return "Student not found";
        }
    }

    public Student getStudent(String firstName, String lastName){
        Student student = null;
        for (Student s : students) {
            if(s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                student = s;
            }
        }
        return student;
    }

    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s", s.getFirstName(), s.getLastName(), s.getBestSubject()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Student> getStudents() {
        return this.students;
    }
}
