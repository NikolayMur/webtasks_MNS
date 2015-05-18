package controllers;

import entity.Discipline;
import entity.Term;
import services.DisciplineServise;
import services.TermService;
import services.impl.DisceplineServiseImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TermsModifyingController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            request.setAttribute("CURRENT_BUTTON", 2);
            boolean isDigit = true;
            TermService termService = new TermServiceImpl();
            DisciplineServise disciplineServise = new DisceplineServiseImpl();

            String strId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.valueOf(strId);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                List<Discipline> selectedDisciplineList = termService.getDisciplinesByIdTerm(id);
//                request.setAttribute("selectedDiscipline", selectedDisciplineList.toArray());

                List<Discipline> disciplineList = disciplineServise.getDisciplines();
                request.setAttribute("disciplines", disciplineList);

                Term term = termService.getById(new Term(id,0,null));
                request.setAttribute("term", term);
            }
            gotoToJSP("/main/term/termCreatingModifying.jsp", request, response);
        }else{


            boolean isDigit = true;
            Boolean resoult = false;
            TermService termService = new TermServiceImpl();
            Term term = new Term();

            String strIdTerm = request.getParameter("id");
            Map<String, String[]> map = request.getParameterMap();
            String[] strIdDisciplines = map.get("multi_list");
            String strDuration = map.get("duration")[0];

            validateRequestTerm(strDuration, strIdDisciplines);

            int id = 0;
            try {
                id = Integer.valueOf(strIdTerm);
            } catch (Exception e) {
                isDigit = false;
            }
            if (isDigit){
                List<Discipline> disciplineList = new LinkedList<Discipline>();
                for (int i = 0; i < strIdDisciplines.length; i++) {
                    disciplineList.add(new Discipline(Integer.valueOf(strIdDisciplines[i]), ""));
                }


                term = new Term(id, Integer.valueOf(strDuration), disciplineList);
                term = termService.update(term);
            }




            if (term.getDuration() > 0 && term.getDiscipline().size() > 0) {
                forwardRequest("/admin/termsList.php", request, response);
            } else {

            }

        }
    }
}
