package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudents();

    Student getById(Student student);

    Student update(Student student);

    boolean delete(int[] idArray);

    boolean create(Student student);

}
