package controllers;

import entity.Student;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentsModifyingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            request.setAttribute("CURRENT_BUTTON", 2);
            boolean isDigit = true;
            String strId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.valueOf(strId);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                Student student = new Student();
                student.setId(id);

                StudentService studentService = new StudentServiceImpl();

                student = studentService.getById(student);
                request.setAttribute("student", student);
            }
            gotoToJSP("/main/student/studentsCreatingModifying.jsp", request, response);
        }else{

            Student student = new Student();
            boolean isDigit = true;
            String firstName = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String groupe = request.getParameter("groupe");
            String strDate = request.getParameter("date");

            validateRequestStudent(lastName, firstName, groupe, strDate);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(strDate);

            String strId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.valueOf(strId);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                student = new Student(id, firstName, lastName, groupe, date);

                StudentService studentService = new StudentServiceImpl();

                student = studentService.update(student);
                request.setAttribute("student", student);
            }

            request.setAttribute("student", student);
            gotoToJSP("/main/student/studentsCreatingModifying.jsp", request,response);

        }
    }
}
