package controllers;

import entity.Student;
import entity.Term;
import services.StudentService;
import services.TermService;
import services.impl.StudentServiceImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MarksListController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {

            //students
            StudentService studentService = new StudentServiceImpl();
            List<Student> studentListList = studentService.getStudents();
            request.setAttribute("students", studentListList);

            //terms
            TermService termService = new TermServiceImpl();
            List<Term> termListList = termService.getTerms();
            request.setAttribute("terms", termListList);

//            //disciplines
//            String strIdStudent = request.getParameter("chooserStd");
//            String strIdTerm = request.getParameter("chooserTerm");
//            if(strIdStudent != null && strIdTerm != null){
//                boolean isDigit = true;
//                int IdStudent = 0;
//                int IdTerm = 0;
//                try {
//                    IdStudent = Integer.valueOf(strIdStudent);
//                    IdTerm = Integer.valueOf(strIdTerm);
//                } catch (Exception e) {
//                    isDigit = false;
//                }
//                if (isDigit) {
//                    MarkService markService = new TermServiceImpl();
//                    List<Marks> marksList = termService.getDisciplinesByIdTerm(IdChooser);
//                    request.setAttribute("disciplines", disciplinesList);
//                    request.setAttribute("selectedTerm", new Term(IdChooser, null, null));
//                }
//            }

             gotoToJSP("/main/mark/marksList.jsp", request, response);
        }else{
            gotoToJSP("/main/mark/marksList.jsp", request, response);
        }
    }
}
