package services;

import entity.Student;

import java.util.List;

public interface StudentService {

	Student update(Student student);

	boolean delete(int[] idArray);

	boolean create(Student student);

	List<Student> getStudents();

	Student getById(Student student);

}
