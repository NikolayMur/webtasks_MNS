package controllers;

import entity.Student;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentsListController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        StudentService studentService = new StudentServiceImpl();
        List<Student> studentListList = studentService.getStudents();

        request.setAttribute("students", studentListList);
        gotoToJSP("/main/student/studentsList.jsp",request,response);

    }
}
