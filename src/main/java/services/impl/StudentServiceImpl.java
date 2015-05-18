package services.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import services.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean delete(int[] idArray) {
        return studentDao.delete(idArray);
    }

    @Override
    public boolean create(Student student) {
        return studentDao.create(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public Student getById(Student student) {
        return studentDao.getById(student);
    }
}
