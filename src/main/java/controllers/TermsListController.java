package controllers;

import entity.Discipline;
import entity.Term;
import services.TermService;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TermsListController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        if (request.getMethod().equals("GET")) {
            TermService termService = new TermServiceImpl();
            List<Term> termListList = termService.getTerms();

            request.setAttribute("terms", termListList);


            String strIdChooser = request.getParameter("chooser");
            if (strIdChooser != null) {
                boolean isDigit = true;
                int IdChooser = 0;
                try {
                    IdChooser = Integer.valueOf(strIdChooser);
                } catch (Exception e) {
                    isDigit = false;
                }
                if (isDigit) {

                    List<Discipline> disciplinesList = termService.getDisciplinesByIdTerm(IdChooser);
                    request.setAttribute("disciplines", disciplinesList);
                    request.setAttribute("selectedTerm", new Term(IdChooser, null, null));
                }
            }

            gotoToJSP("/main/term/termsList.jsp", request, response);
//        } else {
//
//        }
    }
}
