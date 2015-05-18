package dao.impl;

import dao.StudentDao;
import database.DataService;
import entity.Student;

import java.util.List;

public class StudentDaoImpl implements StudentDao{

    private DataService dataService = new DataService();
    @Override
    public List<Student> getStudents() {
        return dataService.getAllStudents();
    }

    @Override
    public Student getById(Student student) {
        return dataService.getStudentById(student);
    }

    @Override
    public Student update(Student student) {
        return dataService.updateStudent(student);
    }

    @Override
    public boolean delete(int[] idArray) {
        return dataService.delStudentsById(idArray);
    }

    @Override
    public boolean create(Student student) {
        return dataService.createStudent(student);
    }
}
