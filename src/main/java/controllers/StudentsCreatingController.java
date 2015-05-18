package controllers;

import entity.Student;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentsCreatingController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            request.setAttribute("CURRENT_BUTTON", 1);
            gotoToJSP("/main/student/studentsCreatingModifying.jsp", request, response);
        }else{
            String firstName = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String groupe = request.getParameter("groupe");
            String strDate = request.getParameter("date");

            validateRequestStudent(lastName, firstName, groupe, strDate);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(strDate);

            StudentService studentService = new StudentServiceImpl();
            Boolean resoult = studentService.create(new Student(0, firstName, lastName, groupe, date));
            if(resoult){
                forwardRequest("/admin/studentsList.php",request, response);
            }else{

            }
        }
    }
}
